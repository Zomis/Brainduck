package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.BrainfuckCompilationException;
import net.zomis.brainf.model.Stack
import net.zomis.brainf.model.ast.BFToken;
import net.zomis.brainf.model.ast.CommentToken;
import net.zomis.brainf.model.ast.GroovyToken;
import net.zomis.brainf.model.ast.Token;
import net.zomis.brainf.model.classic.BrainFCommand;
import net.zomis.brainf.model.groovy.GroovyBFContext;

class Parser(
    groovyContext: GroovyBFContext
) {

    // Reads tokens creates AST
    fun parse(origTokens: List<Token>): SyntaxTree {
        val tokens = origTokens.toList()
        var inner = SyntaxTree()
        val depth = Stack<SyntaxTree>()
        depth.push(inner)

        var lastToken: Token? = null;
        val awaitingTokens = mutableListOf<Token?>()

        var repeatedTokens = 1
        for (token in tokens) {
            inner = depth.peek()
            if (token !is BFToken || token.command != BrainFCommand.WHILE) {
                inner._tokens.add(token)
            }
            if (tokensEqual(token, lastToken)) {
                repeatedTokens++;
                awaitingTokens.add(lastToken)
            } else {
                val syntax = createSyntax(lastToken, repeatedTokens, awaitingTokens);
                repeatedTokens = 1;
                if (syntax != null) {
                    inner.syntax.add(syntax);
                    awaitingTokens.clear();
                }

                if (token is BFToken) {
                    val bft = token as BFToken
                    if (bft.command == BrainFCommand.WHILE) {
                        val loopSyntax = LoopInstructionSyntax()
                        depth.push(loopSyntax)
                        loopSyntax._tokens.add(token)
                    }
                    if (bft.command == BrainFCommand.END_WHILE) {
                        val loopSyntax = depth.pop()
                        if (depth.isEmpty()) {
                            throw BrainfuckCompilationException("No more loops to finish.")
                        }
                        val current: SyntaxTree = depth.peek();
                        current._tokens.addAll(inner.tokens)
                        current.syntax.add(loopSyntax);
                    }
                }
            }
            lastToken = token;
        }

        val syntax = createSyntax(lastToken, repeatedTokens, awaitingTokens)
        if (syntax != null) {
            inner.syntax.add(syntax);
        }

        if (depth.size > 1) {
            val tooManyStartedLoops = depth.size - 1;
            throw BrainfuckCompilationException("There are $tooManyStartedLoops too many started loops.")
        }

        return depth.pop();
    }

    private fun createSyntax(token: Token?, repeatedTokens: Int, awaitingTokens: List<Token?>): Syntax? {
        val syntax = createSyntax(token, repeatedTokens);
        if (syntax != null) {
            syntax._tokens.addAll(awaitingTokens);
            syntax._tokens.add(token)
        }
        return syntax;
    }

    private fun createSyntax(token: Token?, repeatedTokens: Int): Syntax? {
        if (token == null) {
            return null
        }
        // Create new syntax from last token
//        if (token is GroovyToken) {
//            return GroovySyntax(groovyContext, (GroovyToken) token);
//        }
        if (token is CommentToken) {
            return CommentSyntax(token)
        }
        if (token is BFToken) {
            val bft = token
            return when (bft.command) {
                BrainFCommand.ADD -> ChangeValueSyntax(repeatedTokens)
                BrainFCommand.SUBTRACT -> ChangeValueSyntax(-repeatedTokens)
                BrainFCommand.NEXT -> ChangePointerSyntax(repeatedTokens)
                BrainFCommand.PREVIOUS -> ChangePointerSyntax(-repeatedTokens)
                BrainFCommand.WRITE -> PrintSyntax()
                BrainFCommand.READ -> ReadSyntax()
                BrainFCommand.WHILE, BrainFCommand.END_WHILE -> null
                else -> throw IllegalArgumentException("Unexpected BFCommand type: " + bft.command)
            }
        }
        throw IllegalArgumentException("Unexpected token: $token")
    }

    fun tokensEqual(token: Token?, lastToken: Token?): Boolean {
        if (token == null || lastToken == null) {
            return false;
        }
        val sameClass = token::class == lastToken::class
        if (sameClass && token is BFToken) {
            val bfA: BFToken = token
            val bfB = lastToken as BFToken
            when (bfA.command) {
                BrainFCommand.ADD, BrainFCommand.SUBTRACT, BrainFCommand.NEXT, BrainFCommand.PREVIOUS -> {
                    return bfA.command == bfB.command;
                }
            }
        }
        return false;
    }

}

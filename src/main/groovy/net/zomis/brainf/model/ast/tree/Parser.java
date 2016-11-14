package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.ast.BFToken;
import net.zomis.brainf.model.ast.CommentToken;
import net.zomis.brainf.model.ast.GroovyToken;
import net.zomis.brainf.model.ast.Token;
import net.zomis.brainf.model.classic.BrainFCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {

    // Reads tokens creates AST
    public SyntaxTree parse(List<Token> tokens) {
        tokens = new ArrayList<>(tokens);
        SyntaxTree inner = new SyntaxTree();
        Stack<SyntaxTree> depth = new Stack<>();
        depth.add(inner);

        Token lastToken = null;

        int repeatedTokens = 1;
        for (Token token : tokens) {
            inner = depth.peek();
            inner.getTokens().add(token);
            if (tokensEqual(token, lastToken)) {
                repeatedTokens++;
//            } else if (tokensOpposite(token, lastToken)) {
//                repeatedTokens--;
            } else {
                Syntax syntax = createSyntax(lastToken, repeatedTokens);
                repeatedTokens = 1;
                if (syntax != null) {
                    inner.syntax.add(syntax);
                }

                if (token instanceof BFToken) {
                    BFToken bft = (BFToken) token;
                    if (bft.command == BrainFCommand.WHILE) {
                        depth.add(new LoopInstructionSyntax());
                    }
                    if (bft.command == BrainFCommand.END_WHILE) {
                        depth.pop();
                        SyntaxTree current = depth.peek();
                        current.getTokens().addAll(inner.getTokens());
                        current.syntax.add(inner);
                    }
                }

            }
            lastToken = token;
        }

        Syntax syntax = createSyntax(lastToken, repeatedTokens);
        if (syntax != null) {
            inner.syntax.add(syntax);
        }

        return depth.pop();
    }

    private Syntax createSyntax(Token token, int repeatedTokens) {
        if (token == null) {
            return null;
        }
        // Create new syntax from last token
        if (token instanceof GroovyToken) {
            return new GroovySyntax((GroovyToken) token);
        }
        if (token instanceof CommentToken) {
            return new CommentSyntax((CommentToken) token);
        }
        if (token instanceof BFToken) {
            BFToken bft = (BFToken) token;
            switch ((BrainFCommand) bft.command) {
                case ADD:
                    return new ChangeValueSyntax(repeatedTokens);
                case SUBTRACT:
                    return new ChangeValueSyntax(-repeatedTokens);
                case NEXT:
                    return new ChangePointerSyntax(repeatedTokens);
                case PREVIOUS:
                    return new ChangePointerSyntax(-repeatedTokens);
                case WRITE:
                    return new PrintSyntax();
                case READ:
                    return new ReadSyntax();
                case WHILE:
                case END_WHILE:
                    return null;
                default:
                    throw new IllegalArgumentException("Unexpected BFCommand type: " + bft.command);
            }
        }
        throw new IllegalArgumentException("Unexpected token: " + token);
    }

        /*
        * +++++>> 5x ADD and 2x NEXT in one tree
        * [ create a new tree
        * $ something groovy
        *
        * ] finish last new tree
        **/

/*

        for (Token token : tokens) {
            boolean useExisting = false;
            if (repeatedTokens == 0) {
                currentToken = token;
                continue;
            }
            if (token.getClass() != currentToken.getClass()) {
                // Finalize current token.
            }
            if (token instanceof BFToken) {

                BFToken bf = (BFToken) token;
                BrainFCommand command = (BrainFCommand) bf.command;
                if (currentToken instanceof BFToken) {
                    BFToken cu = (BFToken) currentToken;
                    if (cu.command != bf.command) {
                        // Finalize current token
                    } else {
                        useExisting = true;
                    }
                }
                switch (command) {
                    case ADD:
                    case NEXT:
                        if (useExisting) {
                            repeatedTokens++;
                        }
                        break;
                    case SUBTRACT:
                    case PREVIOUS:
                        if (useExisting) {
                            repeatedTokens--;
                        }
                        break;

                    case WHILE:
                    case END_WHILE:
                    case WRITE:
                    case READ:

                }
            }
            if (token instanceof GroovyToken) {
                finishCurrent();
                current.syntax.add(new GroovySyntax(token));
            }
            if (token instanceof CommentToken) {
                finishCurrent();
                current.syntax.add(new CommentSyntax(token));
            }

        }

        //result.syntax.

        return null;
    }
*/

    private boolean tokensEqual(Token token, Token lastToken) {
        if (token == null || lastToken == null) {
            return false;
        }
        boolean sameClass = token.getClass() == lastToken.getClass();
        if (sameClass && token instanceof BFToken) {
            BFToken bfA = (BFToken) token;
            BFToken bfB = (BFToken) lastToken;
            switch ((BrainFCommand) bfA.command) {
                case ADD:
                case SUBTRACT:
                case NEXT:
                case PREVIOUS:
                    return bfA.command == bfB.command;
            }
        }
        return false;
    }

}

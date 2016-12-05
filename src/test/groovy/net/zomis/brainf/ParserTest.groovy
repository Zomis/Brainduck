package net.zomis.brainf

import groovy.transform.CompileStatic
import net.zomis.brainf.model.ast.BFToken
import net.zomis.brainf.model.ast.Lexer
import net.zomis.brainf.model.ast.Token
import net.zomis.brainf.model.ast.tree.ChangePointerSyntax
import net.zomis.brainf.model.ast.tree.ChangeValueSyntax
import net.zomis.brainf.model.ast.tree.LoopInstructionSyntax
import net.zomis.brainf.model.ast.tree.Parser
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.ast.tree.SyntaxTree
import net.zomis.brainf.model.classic.BrainFCommand
import net.zomis.brainf.model.groovy.GroovyBFContext
import org.junit.Test

@CompileStatic
class ParserTest {

    @Test
    public void simpleTest() {
        Parser parser = new Parser(new GroovyBFContext());
        SyntaxTree tree = parser.parse(tokenize("+++>>"));
        assert tree.tokens.size() == 5
        assert (tree.tokens[0] as BFToken).command == BrainFCommand.ADD;
        assert (tree.tokens[1] as BFToken).command == BrainFCommand.ADD;
        assert (tree.tokens[2] as BFToken).command == BrainFCommand.ADD;
        assert (tree.tokens[3] as BFToken).command == BrainFCommand.NEXT;
        assert (tree.tokens[4] as BFToken).command == BrainFCommand.NEXT;
        assert tree.syntax.size() == 2
        assert (tree.syntax[0] as ChangeValueSyntax).value == 3;
        assert (tree.syntax[1] as ChangePointerSyntax).value == 2;
    }

    @Test
    public void loop() {
        Parser parser = new Parser(new GroovyBFContext());
        SyntaxTree tree = parser.parse(tokenize("++[>+[-]]+"));
        assert tree.tokens.size() == 10
        assert tree.syntax.size() == 3
        assert (tree.syntax[0] as ChangeValueSyntax).value == 2;
        assert (tree.syntax[2] as ChangeValueSyntax).value == 1;

        SyntaxTree subTree = (tree.syntax[1] as LoopInstructionSyntax)
        assert subTree.syntax.size() == 3;
        assert (subTree.syntax[0] as ChangePointerSyntax).value == 1
        assert (subTree.syntax[1] as ChangeValueSyntax).value == 1
        assert subTree.tokens.size() == 7

        subTree = (subTree.syntax[2] as LoopInstructionSyntax)
        assert subTree.syntax.size() == 1
        assert (subTree.syntax[0] as ChangeValueSyntax).value == -1
        assert subTree.tokens.size() == 3
    }

    private static List<Token> tokenize(String s) {
        return Lexer.tokenize(s);
    }

}

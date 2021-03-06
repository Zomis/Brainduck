package net.zomis.brainf.ast.transform

import groovy.transform.CompileStatic
import net.zomis.brainf.BrainfuckTest
import net.zomis.brainf.model.ast.transform.OutSyntax
import net.zomis.brainf.model.ast.transform.Translator
import net.zomis.brainf.model.ast.tree.SyntaxTree
import org.junit.Ignore
import org.junit.Test

@CompileStatic
class TranslatorTest2 extends BrainfuckTest {

    SyntaxTree result;

    @Test
    @Ignore
    public void printHelloWorldNoLoop() {
        useFile('helloWorldNoLoop.bf');
        translate()
        assert result.syntax.size() == 1
        assert (result.syntax[0] as OutSyntax).text == "Hello World!"
    }

    void translate() {
        result = new Translator().translate(brain.code.rootTree)
    }

    @Test
    @Ignore
    public void printHelloWorldOneLoop() {
        assert false
    }

    @Test
    @Ignore
    public void printHelloWorldShortest() {
        assert false
    }

}

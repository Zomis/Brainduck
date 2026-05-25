package net.zomis.brainf.ast.transform

import net.zomis.brainf.BrainfuckTest
import net.zomis.brainf.model.ast.transform.OutSyntax
import net.zomis.brainf.model.ast.transform.Translator
import net.zomis.brainf.model.ast.tree.SyntaxTree
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class TranslatorTest2 : BrainfuckTest() {

    lateinit var result: SyntaxTree

    @Test
    @Ignore
    fun printHelloWorldNoLoop() {
        useFile("helloWorldNoLoop.bf")
        translate()
        assertEquals(1, result.syntax.size())
        assertEquals("Hello World!", (result.syntax[0] as OutSyntax).text)
    }

    fun translate() {
        result = Translator().translate(brain.code.rootTree)
    }

    @Test
    @Ignore
    fun printHelloWorldOneLoop() {
        TODO()
    }

    @Test
    @Ignore
    fun printHelloWorldShortest() {
        TODO()
    }

}

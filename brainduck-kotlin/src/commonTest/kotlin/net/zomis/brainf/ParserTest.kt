package net.zomis.brainf

import net.zomis.brainduck.Brainfuck
import net.zomis.brainduck.ast.SyntaxData
import net.zomis.brainduck.ast.TokenData
import kotlin.test.Test
import kotlin.test.assertEquals

class ParserTest {

    @Test
    fun simpleTest() {
        val code = Brainfuck.code("+++>>")
        assertEquals(5, code.syntax.children.sumOf { it.info.tokens.size })
        val tokens = code.syntax.children.flatMap { it.info.tokens }
        assertEquals(TokenData.Plus, tokens[0].data)
        assertEquals(TokenData.Plus, tokens[1].data)
        assertEquals(TokenData.Plus, tokens[2].data)
        assertEquals(TokenData.MoveRight, tokens[3].data)
        assertEquals(TokenData.MoveRight, tokens[4].data)
        assertEquals(2, code.syntax.children.size)
        assertEquals(3, (code.syntax.children[0].data as SyntaxData.ChangeValue).delta)
        assertEquals(2, (code.syntax.children[1].data as SyntaxData.Move).delta)
    }

    @Test
    fun loop() {
        val code = Brainfuck.code("++[>+[-]]+")
        assertEquals(10, code.syntax.children.sumOf { it.info.tokens.size })
        assertEquals(3, code.syntax.children.size)
        val tokens = code.syntax.children.flatMap { it.info.tokens }
        assertEquals(2, (code.syntax.children[0].data as SyntaxData.ChangeValue).delta)
        assertEquals(1, (code.syntax.children[2].data as SyntaxData.ChangeValue).delta)

        val subTree = code.syntax.children[1].data as SyntaxData.WhileNotZero
        assertEquals(4, subTree.children.size)
        assertEquals(1, (subTree.children[0].data as SyntaxData.Move).delta)
        assertEquals(1, (subTree.children[1].data as SyntaxData.ChangeValue).delta)
        assertEquals(7, code.syntax.children[1].info.tokens.size)
        assertEquals(SyntaxData.EndWhile, subTree.children[3].data)

        val nestedSubTree = subTree.children[2].data as SyntaxData.WhileNotZero
        assertEquals(2, nestedSubTree.children.size)
        assertEquals(-1, (nestedSubTree.children[0].data as SyntaxData.ChangeValue).delta)
        assertEquals(SyntaxData.EndWhile, nestedSubTree.children[1].data)
        assertEquals(3, subTree.children[2].info.tokens.size)
    }

}

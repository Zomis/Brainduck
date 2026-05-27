package net.zomis.brainduck

import net.zomis.brainduck.ast.Lexer

object Brainfuck {

    fun tokenize(text: String): TokenizedProgram {
        return Lexer().tokenize(text)
    }

    fun code(text: String): BrainfuckCode = tokenize(text).parse()

}

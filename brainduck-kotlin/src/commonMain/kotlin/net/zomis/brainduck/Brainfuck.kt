package net.zomis.brainduck

import net.zomis.brainduck.ast.Lexer
import net.zomis.brainduck.runner.LoopStartRunner
import net.zomis.brainduck.runner.Runner
import net.zomis.brainduck.runner.StepRunner
import net.zomis.brainduck.runner.UntilEnd
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@OptIn(ExperimentalJsExport::class)
@JsExport
object Brainfuck {

    fun tokenize(text: String): TokenizedProgram {
        return Lexer().tokenize(text)
    }

    fun code(text: String): BrainfuckCode = tokenize(text).parse()

    object Run {
        val all = UntilEnd
        val step: Runner get() = TODO()
        val stepSyntax = StepRunner(steps = 1)
        val loopStart = LoopStartRunner
        fun steps(steps: Int) = StepRunner(steps)
    }

}

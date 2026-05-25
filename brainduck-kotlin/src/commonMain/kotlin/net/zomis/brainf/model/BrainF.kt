package net.zomis.brainf.model;

import net.zomis.brainf.model.ast.Lexer;
import net.zomis.brainf.model.ast.tree.Parser;
import net.zomis.brainf.model.classic.BrainfuckConverter
import net.zomis.brainf.model.groovy.GroovyBFContext;
import net.zomis.brainf.model.input.QueueInput

object BrainF {
	fun createUsingQueueWithMemorySize(
        input: Iterator<Int>,
        memorySize: Int,
        output: BrainfuckOutput,
    ): BrainfuckRunner {
		return BrainfuckRunner(BrainfuckMemory(memorySize), BrainfuckCode(), QueueInput(input), output)
	}

    fun code(code: String): BrainfuckCode {
        val bfCode = BrainfuckCode()
        val tokens = Lexer.tokenize(code)
        bfCode.setRootTree(Parser(GroovyBFContext()).parse(tokens))
        bfCode.source = ListCode.create(BrainfuckConverter(), code)
        return bfCode
    }
}

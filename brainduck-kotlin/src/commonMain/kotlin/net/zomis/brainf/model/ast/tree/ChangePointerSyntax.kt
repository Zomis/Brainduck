package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;
import kotlin.math.absoluteValue

class ChangePointerSyntax(val value: Int) : Syntax(), SteppableSyntax {

    override fun getValue(): Int = value

    override fun perform(runner: BrainfuckRunner) {
        performTimes(runner, value.absoluteValue)
    }

    override fun toString(): String {
        return "ChangePointerSyntax{" +
                "value=" + value +
                '}';
    }

    override fun performTimes(runner: BrainfuckRunner, steps: Int) {
        val steps2 = if (value >= 0) steps else -steps
        runner.memory.setMemoryIndex(runner.memory.memoryIndex + steps2)
    }

}

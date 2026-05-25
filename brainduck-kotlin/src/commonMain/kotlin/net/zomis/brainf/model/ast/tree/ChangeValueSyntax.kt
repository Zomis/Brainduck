package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner
import kotlin.math.absoluteValue

class ChangeValueSyntax(val value: Int) : Syntax(), SteppableSyntax {

    override fun getValue(): Int = value

    override fun perform(runner: BrainfuckRunner) {
        performTimes(runner, value.absoluteValue)
    }

    override fun toString(): String {
        return "ChangeValueSyntax{" +
                "value=" + value +
                '}';
    }

    override fun performTimes(runner: BrainfuckRunner, steps: Int) {
        val steps2 = if (value >= 0) steps else -steps
        runner.memory.changeMemory(steps2)
    }

}

package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;

class PrintSyntax : Syntax() {

    override fun perform(runner: BrainfuckRunner) {
        val write = runner.memory.value
        runner.appendOutput(write.toChar());
    }

}

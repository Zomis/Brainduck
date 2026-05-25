package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;

class ReadSyntax : Syntax() {

    override fun perform(runner: BrainfuckRunner) {
        val value = runner.input.read()
        runner.memory.setMemory(value)
    }

}

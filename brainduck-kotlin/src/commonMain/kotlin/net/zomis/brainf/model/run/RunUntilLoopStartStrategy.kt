package net.zomis.brainf.model.run

import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ast.tree.LoopInstructionSyntax

class RunUntilLoopStartStrategy : RunStrategy {

    private var found: Boolean = false

    fun stopOnNext(): Boolean {
        found = true
        return true
    }

    override fun start(runner: BrainfuckRunner): Boolean {
        found = false
        return true
    }

    override fun next(runner: BrainfuckRunner): Boolean {
        if (runner.code.isFinished() || found) {
            return false;
        }
        // is able to run the optimized commands (+++++, <<<<<<)
        if (runner.code.currentSyntax !is LoopInstructionSyntax) {
            runner.runSyntax()
            return true
        } else {
            return stopOnNext()
        }
//        if (next == BrainFCommand.END_WHILE && memoryNotZero) {
//        if (next == BrainFCommand.WHILE && memoryNotZero) {
    }

}

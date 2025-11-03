package net.zomis.brainf.model.run

import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ast.tree.LoopInstructionSyntax
import net.zomis.brainf.model.classic.BrainFCommand

class RunUntilLoopStartStrategy implements RunStrategy {

    private boolean found

    boolean stopOnNext() {
        found = true
        return true
    }

    @Override
    boolean start(BrainfuckRunner runner) {
        found = false
        return true
    }

    @Override
    boolean next(BrainfuckRunner runner) {
        if (runner.code.isFinished() || found) {
            return false;
        }
        // is able to run the optimized commands (+++++, <<<<<<)
        if (!(runner.code.currentSyntax instanceof LoopInstructionSyntax)) {
            runner.runSyntax()
            return true
        } else {
            return stopOnNext()
        }
//        if (next == BrainFCommand.END_WHILE && memoryNotZero) {
//        if (next == BrainFCommand.WHILE && memoryNotZero) {
    }

}

package net.zomis.brainf.model.run

import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
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
        BrainfuckCommand next = runner.code.nextCommand
        if (next == null || found) {
            return false
        }
        next = runner.step()
        boolean memoryNotZero = runner.memory.value != 0
        if (next == BrainFCommand.END_WHILE && memoryNotZero) {
            return stopOnNext()
        }
        if (next == BrainFCommand.WHILE && memoryNotZero) {
            return stopOnNext()
        }
        return true
    }

}

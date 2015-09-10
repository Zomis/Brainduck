package net.zomis.brainf.ui.run

import net.zomis.brainf.BrainFCommand
import net.zomis.brainf.model.BrainfuckRunner

class StepOutStrategy implements RunStrategy {

    @Override
    boolean next(BrainfuckRunner runner) {
        BrainFCommand comm = runner.getCode().getNextCommand()
        int value = runner.getMemory().getMemory()
        if (comm == BrainFCommand.END_WHILE && value == 0) {
            return false
        }
        runner.step();
        return true
    }

}

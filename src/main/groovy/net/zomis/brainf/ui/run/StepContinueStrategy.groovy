package net.zomis.brainf.ui.run

import net.zomis.brainf.BrainFCommand
import net.zomis.brainf.model.BrainfuckRunner

class StepContinueStrategy implements RunStrategy {

    @Override
    boolean next(BrainfuckRunner runner) {
        BrainFCommand comm = runner.getCode().getNextCommand()
        if (comm == BrainFCommand.END_WHILE) {
            return false
        }
        runner.step();
        return true
    }

}

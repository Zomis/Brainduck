package net.zomis.brainf.ui.run

import net.zomis.brainf.BrainFCommand
import net.zomis.brainf.model.BrainfuckRunner

class SingleStepStrategy implements RunStrategy {

    @Override
    boolean next(BrainfuckRunner runner) {
        BrainFCommand comm = runner.step();
        if (comm != BrainFCommand.NONE) {
            System.out.println("Step: " + comm);
        }
        return comm == BrainFCommand.NONE;
    }

}

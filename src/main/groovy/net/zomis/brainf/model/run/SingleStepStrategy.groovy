package net.zomis.brainf.model.run

import net.zomis.brainf.model.classic.BrainFCommand
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner

class SingleStepStrategy implements RunStrategy {

    private boolean encountered

    @Override
    boolean start(BrainfuckRunner runner) {
        encountered = false
        return true
    }

    @Override
    boolean next(BrainfuckRunner runner) {
        if (!encountered) {
            BrainfuckCommand comm = runner.step();
            if (comm != BrainFCommand.NONE) {
                encountered = true
                System.out.println("Step: " + comm);
            }
            return true
        } else {
            BrainfuckCommand comm = runner.code.getNextCommand()
            if (comm == BrainFCommand.NONE) {
                runner.step()
            }
            return (comm == BrainFCommand.NONE)
        }
    }

}

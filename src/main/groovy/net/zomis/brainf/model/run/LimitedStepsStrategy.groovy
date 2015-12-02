package net.zomis.brainf.model.run

import net.zomis.brainf.model.classic.BrainFCommand
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner

class LimitedStepsStrategy implements RunStrategy {

    private int remaining
    private final int count

    LimitedStepsStrategy() {
        this(1)
    }

    LimitedStepsStrategy(int count) {
        this.count = count
    }

    @Override
    boolean start(BrainfuckRunner runner) {
        remaining = count
        return true
    }

    @Override
    boolean next(BrainfuckRunner runner) {
        if (remaining > 0) {
            BrainfuckCommand comm = runner.step();
            if (comm != BrainFCommand.NONE) {
                remaining--
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

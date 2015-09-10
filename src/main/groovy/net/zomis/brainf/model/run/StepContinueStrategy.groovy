package net.zomis.brainf.model.run

import net.zomis.brainf.BrainFCommand
import net.zomis.brainf.model.BrainfuckRunner

class StepContinueStrategy implements RunStrategy {

    private int loops

    @Override
    boolean start(BrainfuckRunner runner) {
        loops = 0
        if (runner.getCode().getNextCommand() == BrainFCommand.WHILE) {
            return false
        }
        int matching = runner.getCode().findMatching(BrainFCommand.END_WHILE, BrainFCommand.WHILE, 1)
        return matching != -1
    }

    @Override
    boolean next(BrainfuckRunner runner) {
        BrainFCommand comm = runner.getCode().getNextCommand()
        if (comm == BrainFCommand.WHILE) {
            loops++
        }
        if (comm == BrainFCommand.END_WHILE && runner.getMemory().getMemory() == 0) {
            loops--
        }
        if (comm == BrainFCommand.END_WHILE && loops <= 0) {
            runner.step()
            return false
        }
        runner.step();
        return true
    }

}

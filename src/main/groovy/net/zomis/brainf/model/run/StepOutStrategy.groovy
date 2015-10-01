package net.zomis.brainf.model.run

import net.zomis.brainf.model.classic.BrainFCommand
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner

class StepOutStrategy implements RunStrategy {

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
        BrainfuckCommand comm = runner.getCode().getNextCommand()
        int value = runner.getMemory().getMemory()
        if (comm == BrainFCommand.WHILE) {
            loops++
        }
        if (comm == BrainFCommand.END_WHILE && value == 0) {
            loops--
        }
        if (comm == BrainFCommand.END_WHILE && loops < 0 && value == 0) {
            runner.step()
            return false
        }
        runner.step();
        return true
    }

}

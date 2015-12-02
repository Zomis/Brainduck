package net.zomis.brainf.model.run

import net.zomis.brainf.model.classic.BrainFCommand
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner

class StepContinueStrategy implements RunStrategy {

    private int loopStart
    private int loopEnd
    private int nextLoop
    private boolean performedOnce
    private final RunStrategy singleStep = new LimitedStepsStrategy()

    @Override
    boolean start(BrainfuckRunner runner) {
        loopStart = runner.code.findMatching(BrainFCommand.WHILE,
            BrainFCommand.END_WHILE, -1)
        loopEnd = runner.code.findMatching(loopStart, BrainFCommand.END_WHILE,
                BrainFCommand.WHILE, 1)
        nextLoop = runner.code.findMatching(BrainFCommand.WHILE,
                BrainFCommand.END_WHILE, 1)
        performedOnce = false
        println 'loopStart at ' + loopStart + ' and end at ' + loopEnd + ' next loop at ' + nextLoop

        return singleStep.start(runner)
    }

    @Override
    boolean next(BrainfuckRunner runner) {
        BrainfuckCommand comm = runner.getCode().getNextCommand()
        if (comm == null) {
            return false
        }

        int codeIndex = runner.code.commandIndex
        if (loopStart != -1 && loopEnd != -1) {
            // if inside loop, find the correct `END_WHILE` inside this loop and go to it and one step beyond
            boolean perform = codeIndex != loopStart + 1 && codeIndex != loopEnd + 1
            if (!performedOnce) {
                perform = true
                performedOnce = true
            }
            if (perform) {
                runner.step()
            }
            return perform
        } else if (nextLoop != -1) {
            // Not inside loop, go to next starting loop if there is one
            if (codeIndex != nextLoop + 1) {
                runner.step()
                return true
            }
            return false
        } else {
            // if not inside loop and no next loop, do the same as `StepNext`
            return singleStep.next(runner)
        }
    }

}

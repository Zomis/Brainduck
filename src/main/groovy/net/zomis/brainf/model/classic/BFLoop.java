package net.zomis.brainf.model.classic;

import net.zomis.brainf.model.BrainfuckCommand;
import net.zomis.brainf.model.BrainfuckRunner;

@Deprecated
public class BFLoop implements BrainfuckCommand {

    private final int i;

    public BFLoop(int i) {
        this.i = i;
    }


    @Override
    public void perform(BrainfuckRunner runner) {
        if (i == 1) {
            if (runner.getMemory().getValue() == 0) {
                runner.getCode().gotoMatching(BrainFCommand.END_WHILE, BrainFCommand.WHILE, 1);
            }
        } else if (i == -1) {
            if (runner.getMemory().getValue() != 0) {
                runner.getCode().gotoMatching(BrainFCommand.WHILE, BrainFCommand.END_WHILE, -1);
            }
        } else {
            throw new IllegalStateException("Invalid i: " + i);
        }
    }
}

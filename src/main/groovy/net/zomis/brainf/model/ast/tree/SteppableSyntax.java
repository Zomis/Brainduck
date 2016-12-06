package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;

public interface SteppableSyntax {

    int getValue();
    void performTimes(BrainfuckRunner runner, int steps);
    default int getTimes() {
        return Math.abs(getValue());
    }

}

package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;

public interface SteppableSyntax {

    void performTimes(BrainfuckRunner runner, int steps);
    int getTimes();

}

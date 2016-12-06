package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;

public class PrintSyntax extends Syntax {

    @Override
    public void perform(BrainfuckRunner runner) {
        char write = (char) runner.getMemory().getValue();
        runner.appendOutput(write);
    }

}

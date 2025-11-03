package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckException;
import net.zomis.brainf.model.BrainfuckRunner;

public class ReadSyntax extends Syntax {

    @Override
    public void perform(BrainfuckRunner runner) {
        int value;
        try {
            value = runner.getInput().read();
        } catch (BrainfuckException e) {
            throw new RuntimeException(e);
        }
        runner.getMemory().setMemory(value);
    }

}

package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;

public class ChangePointerSyntax extends Syntax {

    private final int value;

    public ChangePointerSyntax(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void perform(BrainfuckRunner runner) {

    }

}

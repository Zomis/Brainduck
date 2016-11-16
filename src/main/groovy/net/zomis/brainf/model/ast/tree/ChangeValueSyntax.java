package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;

public class ChangeValueSyntax extends Syntax {

    private final int value;

    public ChangeValueSyntax(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void perform(BrainfuckRunner runner) {

    }

    @Override
    public String toString() {
        return "ChangeValueSyntax{" +
                "value=" + value +
                '}';
    }
}
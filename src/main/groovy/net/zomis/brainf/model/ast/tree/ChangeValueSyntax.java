package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;

public class ChangeValueSyntax extends Syntax implements SteppableSyntax {

    private final int value;

    public ChangeValueSyntax(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void perform(BrainfuckRunner runner) {
        performTimes(runner, Math.abs(value));
    }

    @Override
    public String toString() {
        return "ChangeValueSyntax{" +
                "value=" + value +
                '}';
    }

    @Override
    public void performTimes(BrainfuckRunner runner, int steps) {
        steps = value >= 0 ? steps : -steps;
        runner.getMemory().changeMemory(steps);
    }

}

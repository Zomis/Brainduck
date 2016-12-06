package net.zomis.brainf.model.groovy;

import net.zomis.brainf.model.BrainfuckCommand;
import net.zomis.brainf.model.BrainfuckListener;
import net.zomis.brainf.model.BrainfuckRunner;
import net.zomis.brainf.model.ast.tree.ChangePointerSyntax;
import net.zomis.brainf.model.ast.tree.ChangeValueSyntax;
import net.zomis.brainf.model.ast.tree.Syntax;
import net.zomis.brainf.model.classic.BrainFCommand;

public class GroovyListener implements BrainfuckListener {

    private final GroovyBFContext context;

    public GroovyListener(final GroovyBFContext context) {
        this.context = context;
    }

    @Override
    public void beforePerform(BrainfuckRunner runner, Syntax command) {
        if (command instanceof ChangeValueSyntax) {
            ChangeValueSyntax valueSyntax = (ChangeValueSyntax) command;
            long currentValue = runner.getMemory().getValue();
            int change = valueSyntax.getValue();
            long resultingValue = currentValue + change;
            boolean overflow = resultingValue > runner.getMemory().getMaxValue();
            boolean underflow = resultingValue < runner.getMemory().getMinValue();
            switch (context.getValueWrap()) {
                case BLOCK:
                    if (overflow) {
                        runner.getMemory().setMemory(runner.getMemory().getMaxValue() - change);
                    } else if (underflow) {
                        runner.getMemory().setMemory(runner.getMemory().getMinValue() + change);
                    }
                    break;
                case CRASH:
                    if (overflow || underflow) {
                        throw new AssertionError("Memory value wrapping is not allowed");
                    }
                default:
            }
            return;
        }

        if (command instanceof ChangePointerSyntax) {
            int maxMemory = runner.getMemory().getMemorySize() - 1;

            ChangePointerSyntax pointerSyntax = (ChangePointerSyntax) command;
            long currentValue = runner.getMemory().getMemoryIndex();
            int change = pointerSyntax.getValue();
            long resultingValue = currentValue + change;
            boolean overflow = resultingValue > maxMemory;
            boolean underflow = resultingValue < 0;

            switch (context.getValueWrap()) {
                case BLOCK:
                    if (overflow) {
                        runner.getMemory().setMemoryIndex(maxMemory - change);
                    } else if (underflow) {
                        runner.getMemory().setMemoryIndex(Math.abs(change));
                    }
                    break;
                case CRASH:
                    if (overflow || underflow) {
                        throw new AssertionError("Memory index wrapping is not allowed");
                    }
                default:
            }
        }
    }

    @Override
    public void afterPerform(BrainfuckRunner runner, Syntax command) {

    }

}

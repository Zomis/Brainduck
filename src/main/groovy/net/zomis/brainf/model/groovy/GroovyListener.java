package net.zomis.brainf.model.groovy;

import net.zomis.brainf.model.BrainfuckCommand;
import net.zomis.brainf.model.BrainfuckListener;
import net.zomis.brainf.model.BrainfuckRunner;
import net.zomis.brainf.model.classic.BrainFCommand;

public class GroovyListener implements BrainfuckListener {

    private final GroovyBFContext context;

    public GroovyListener(final GroovyBFContext context) {
        this.context = context;
    }

    @Override
    public void beforePerform(BrainfuckRunner runner, BrainfuckCommand command) {
        if (command == BrainFCommand.ADD) {
            if (runner.getMemory().getValue() == runner.getMemory().getMaxValue()) {
                switch (context.getValueWrap()) {
                    case BLOCK:
                        runner.getMemory().setMemory(runner.getMemory().getMaxValue() - 1);
                        break;
                    case CRASH:
                        throw new AssertionError("Memory value wrapping is not allowed");
                    default:
                }
            }
            return;
        }
        if (command == BrainFCommand.SUBTRACT) {
            if (runner.getMemory().getValue() == runner.getMemory().getMinValue()) {
                switch (context.getValueWrap()) {
                    case BLOCK:
                        runner.getMemory().setMemory(runner.getMemory().getMinValue() + 1);
                        break;
                    case CRASH:
                        throw new AssertionError("Memory value wrapping is not allowed");
                    default:
                }
            }
        }
        if (command == BrainFCommand.NEXT) {
            int maxMemory = runner.getMemory().getMemorySize() - 1;
            if (runner.getMemory().getMemoryIndex() == maxMemory) {
                switch (context.getValueWrap()) {
                    case BLOCK:
                        runner.getMemory().setMemoryIndex(maxMemory - 1);
                        break;
                    case CRASH:
                        throw new AssertionError("Memory index wrapping is not allowed");
                    default:
                }
            }
        }
        if (command == BrainFCommand.PREVIOUS) {
            if (runner.getMemory().getMemoryIndex() == 0) {
                switch (context.getValueWrap()) {
                    case BLOCK:
                        runner.getMemory().setMemoryIndex(1);
                        break;
                    case CRASH:
                        throw new AssertionError("Memory index wrapping is not allowed");
                    default:
                }
            }
        }
    }

    @Override
    public void afterPerform(BrainfuckRunner runner, BrainfuckCommand command) {

    }

}

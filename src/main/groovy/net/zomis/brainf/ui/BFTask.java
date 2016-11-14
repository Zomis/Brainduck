package net.zomis.brainf.ui;

import javafx.concurrent.Task;
import net.zomis.brainf.model.BrainfuckRunner;
import net.zomis.brainf.model.groovy.GroovySupportConverter;
import net.zomis.brainf.model.run.RunStrategy;

import java.util.concurrent.atomic.AtomicLong;

public class BFTask extends Task<Void> {

    private final BrainfuckRunner brain;
    private final RunStrategy strategy;
    private final GroovySupportConverter converter;

    public BFTask(BrainfuckRunner brain, GroovySupportConverter converter, RunStrategy strategy) {
        this.brain = brain;
        this.converter = converter;
        this.strategy = strategy;
    }

    @Override
    protected Void call() throws Exception {
        try {
            final AtomicLong runTimes = new AtomicLong();
            int count = brain.run(new RunStrategy() {
                @Override
                public boolean start(BrainfuckRunner runner) {
                    return strategy.start(runner);
                }

                @Override
                public boolean next(BrainfuckRunner runner) {
                    if (Thread.interrupted()) {
                        updateMessage("Interrupted!");
                        return false;
                    }
                    if (isCancelled()) {
                        return false;
                    }
                    int oldCommandIndex = runner.getCode().getCommandIndex();
                    try {
                        boolean result = strategy.next(runner);
                        if (converter.getGroovyContext().isPause()) {
                            converter.getGroovyContext().setPause(false);
                            return false;
                        }
                        return result;
                    } catch (AssertionError ex) {
                        ex.printStackTrace();
                        runner.getCode().setCommandIndex(oldCommandIndex);
                        return false;
                    }
                }
            });
            if (count == 0) {
                System.out.println(strategy.toString() + " not started");
            }
            updateProgress(runTimes.get(), 0);
        } catch (AssertionError | RuntimeException ex) {
            ex.printStackTrace();
        }
        return null;
    }



}

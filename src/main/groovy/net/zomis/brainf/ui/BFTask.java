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
    private final String name;

    public BFTask(String name, BrainfuckRunner brain, GroovySupportConverter converter, RunStrategy strategy) {
        this.name = name;
        this.brain = brain;
        this.converter = converter;
        this.strategy = strategy;
    }

    @Override
    protected Void call() throws Exception {
        String strategyName = strategy.getClass().getSimpleName().replace("Strategy", "");
        updateTitle(name + ": " + strategyName);
        try {
            int count = brain.run(new RunStrategy() {
                private int lowestCode;
                private int highestCode;
                private long runTimes;

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
                        updateMessage("Cancelled!");
                        return false;
                    }
                    int oldCommandIndex = runner.getCode().getCommandIndex();
                    try {
                        boolean result = strategy.next(runner);
                        runTimes++;
                        updateProgressMessage(runner);
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

                private void updateProgressMessage(BrainfuckRunner runner) {
                    int codeIndex = runner.getCode().getCommandIndex();
                    lowestCode = Math.min(codeIndex, lowestCode);
                    highestCode = Math.max(codeIndex, highestCode);
                    updateMessage("Running step " + runTimes +
                        ". Lowest code used " + lowestCode +
                        ". Current code " + codeIndex +
                        ". Highest code " + highestCode);
                    updateProgress(runTimes, -1);
                }
            });
            if (count != 0) {
                updateMessage("Completed with " + count + " steps.");
            } else {
                updateMessage("Unable to start.");
            }
            updateProgress(count, count);
        } catch (AssertionError | RuntimeException ex) {
            updateMessage("Failed: " + ex);
            ex.printStackTrace();
        }
        return null;
    }



}

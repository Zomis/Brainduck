package net.zomis.brainf.ui;

import javafx.concurrent.Task;
import net.zomis.brainf.analyze.AnalyzeFactory;
import net.zomis.brainf.analyze.Brainalyze;
import net.zomis.brainf.analyze.analyzers.BrainfuckAnalyzers;
import net.zomis.brainf.model.BrainfuckRunner;
import net.zomis.brainf.model.groovy.GroovyBFContext;

import java.util.concurrent.TimeUnit;

public class BFTaskAnalyze extends Task<Brainalyze> {

    private final BrainfuckRunner analyzeRunner;
    private final GroovyBFContext groovyContext;
    private final String name;

    public BFTaskAnalyze(String name, BrainfuckRunner analyzeRunner, GroovyBFContext groovyContext) {
        this.name = name;
        this.analyzeRunner = analyzeRunner;
        this.groovyContext = groovyContext;
    }

    @Override
    protected Brainalyze call() throws Exception {
        updateTitle("Analyzing " + name);
        updateMessage("Analyzing...");
        long nanoTime = System.nanoTime();
        Brainalyze analyze = new AnalyzeFactory()
                .addAnalyzers(BrainfuckAnalyzers.getAvailableAnalyzers())
                .analyze(analyzeRunner, groovyContext);

        nanoTime = System.nanoTime() - nanoTime;
        analyze.print();
        updateProgress(100, 100);
        updateMessage("Analyze completed in " + TimeUnit.NANOSECONDS.toMillis(nanoTime)
            + " milliseconds");
        return analyze;
    }

}

package net.zomis.brainf.analyze

import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckListener
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.groovy.GroovyBFContext

class AnalyzeFactory {

    private final List<BrainfuckAnalyzer> analyzers = []

    AnalyzeFactory() {
    }

    AnalyzeFactory addAnalyzers(BrainfuckAnalyzer... analyzers) {
        for (BrainfuckAnalyzer analyzer : analyzers) {
            if (analyzer == null) {
                throw new IllegalArgumentException('Cannot add null analyzer')
            }
            this.analyzers.add(analyzer)
        }
        this
    }

    Brainalyze analyze(BrainfuckRunner runner, GroovyBFContext groovyContext) {
        long time = System.nanoTime()
        Brainalyze analyze = new Brainalyze(runner, groovyContext)
        for (BrainfuckAnalyzer analyzer : analyzers) {
            analyzer.beforeStart(runner)
            analyze.analysis.put(analyzer.class, analyzer)
        }
        runner.setListener(new AnalyzeProgress(analyzers, analyze))
        runner.run()
        analyzers*.after(analyze, runner)
        analyze.timeSpent = System.nanoTime() - time
        analyze
    }

    private static class AnalyzeProgress implements BrainfuckListener {
        final List<BrainfuckAnalyzer> analyzers
        final Brainalyze analyze

        AnalyzeProgress(List<BrainfuckAnalyzer> analyzers, Brainalyze brainalyze) {
            this.analyzers = analyzers
            this.analyze = brainalyze
        }

        @Override
        void beforePerform(BrainfuckRunner runner, BrainfuckCommand command) {
            MemoryCell cell = analyze.cell(runner.memory.memoryIndex)
            analyzers*.beforePerform(cell, runner, command)
        }

        @Override
        void afterPerform(BrainfuckRunner runner, BrainfuckCommand command) {
            MemoryCell cell = analyze.cell(runner.memory.memoryIndex)
            analyzers*.afterPerform(cell, runner, command)
        }
    }

}

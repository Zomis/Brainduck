package net.zomis.brainf.analyze

import groovy.transform.CompileStatic
import net.zomis.brainf.model.BrainfuckListener
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.groovy.GroovyBFContext
import net.zomis.brainf.model.run.UntilEndStrategy

@CompileStatic
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
        runner.run(new UntilEndStrategy())
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
        void beforePerform(BrainfuckRunner runner, Syntax command) {
            MemoryCell cell = analyze.cell(runner.memory.memoryIndex)
            analyzers*.beforePerform(cell, runner, command)
        }

        @Override
        void afterPerform(BrainfuckRunner runner, Syntax command) {
            MemoryCell cell = analyze.cell(runner.memory.memoryIndex)
            analyzers*.afterPerform(cell, runner, command)
        }

        @Override
        void beforeEndWhile(BrainfuckRunner runner) {
            MemoryCell cell = analyze.cell(runner.memory.memoryIndex)
            analyzers*.beforeEndWhile(cell, runner)
        }

        @Override
        void beforeWhile(BrainfuckRunner runner) {
            MemoryCell cell = analyze.cell(runner.memory.memoryIndex)
            analyzers*.beforeWhile(cell, runner)
        }

        @Override
        void afterEndWhile(BrainfuckRunner runner) {
            MemoryCell cell = analyze.cell(runner.memory.memoryIndex)
            analyzers*.afterEndWhile(cell, runner)
        }

        @Override
        void afterWhile(BrainfuckRunner runner) {
            MemoryCell cell = analyze.cell(runner.memory.memoryIndex)
            analyzers*.afterWhile(cell, runner)
        }
    }

}

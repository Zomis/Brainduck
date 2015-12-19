package net.zomis.brainf.analyze.analyzers

import net.zomis.brainf.analyze.BrainfuckAnalyzer

class BrainfuckAnalyzers {

    public static BrainfuckAnalyzer[] getAvailableAnalyzers() {
        BrainfuckAnalyzer[] analyzers = [
                new GroovyCommandAnalysis(),
                new IOAnalysis(),
                new MemoryValues(),
                new ReadWriteAnalysis(),
                new WhileLoopAnalysis(),
                new CommandCountAnalysis(),
                new MemoryIndexAnalysis(),
                new PlusMinusOptimizer(),
                new CodeCellRelationAnalysis(),
        ]
        analyzers
    }

}

package net.zomis.brainf.analyze.analyzers

import net.zomis.brainf.analyze.BrainfuckAnalyzer

class BrainfuckAnalyzers {

    public static BrainfuckAnalyzer[] getAvailableAnalyzers() {
        // TODO Change BF Analyzers to use AST
        BrainfuckAnalyzer[] analyzers = [
                new GroovyCommandAnalysis(), // Static analysis
                new IOAnalysis(),
                new MemoryValues(),
                new ReadWriteAnalysis(),
                new WhileLoopAnalysis(),
                new CommandCountAnalysis(), // Code commands is static analysis
                new MemoryIndexAnalysis(),
                new PlusMinusOptimizer(), // Static analysis
                new CodeCellRelationAnalysis(),
        ]
        analyzers
    }

}

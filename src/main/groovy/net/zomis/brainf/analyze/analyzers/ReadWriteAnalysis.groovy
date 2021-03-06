package net.zomis.brainf.analyze.analyzers

import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.analyze.MemoryCell
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ast.tree.ChangeValueSyntax
import net.zomis.brainf.model.ast.tree.PrintSyntax
import net.zomis.brainf.model.ast.tree.ReadSyntax
import net.zomis.brainf.model.ast.tree.SteppableSyntax
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.ast.tree.SyntaxTree
import net.zomis.brainf.model.classic.BrainFCommand

class ReadWriteAnalysis implements BrainfuckAnalyzer {

    static class ReadWriteData {
        long readCount
        long writeCount

        String toString() {
            String.format('Reads: %6d\tWrites: %6d', readCount, writeCount)
        }
    }
    int maxMemory
    int cellsUsed

    @Override
    Object createMemoryData() {
        return new ReadWriteData()
    }

    @Override
    void after(Brainalyze analyze, BrainfuckRunner runner) {
        int used = 0
        for (int i = 0; i <= maxMemory; i++) {
            ReadWriteData data = analyze.cell(i).data(ReadWriteData)
            if (data && (data.readCount > 0 || data.writeCount > 0)) {
                used++
            }
        }
        this.cellsUsed = used
    }

    @Override
    void beforePerform(MemoryCell cell, BrainfuckRunner runner, Syntax cmd) {
        int times = cmd instanceof SteppableSyntax ? (cmd as SteppableSyntax).times : 1;
        if (cmd instanceof ChangeValueSyntax || cmd instanceof ReadSyntax) {
            cell.data(this, ReadWriteData).writeCount += times;
            maxMemory = Math.max(maxMemory, runner.memory.memoryIndex)
        }
        if (cmd instanceof PrintSyntax) {
            markCellRead(cell, runner);
        }
    }

    @Override
    void beforeEndWhile(MemoryCell cell, BrainfuckRunner runner) {
        markCellRead(cell, runner);
    }

    @Override
    void beforeWhile(MemoryCell cell, BrainfuckRunner runner) {
        markCellRead(cell, runner);
    }

    private void markCellRead(MemoryCell cell, BrainfuckRunner runner) {
        cell.data(this, ReadWriteData).readCount++
        maxMemory = Math.max(maxMemory, runner.memory.memoryIndex)
    }

    @Override
    void print() {
        println "Total memory used = $cellsUsed"
    }

}

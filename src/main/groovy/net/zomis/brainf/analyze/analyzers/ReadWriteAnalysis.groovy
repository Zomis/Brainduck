package net.zomis.brainf.analyze.analyzers

import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.analyze.MemoryCell
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
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
    void beforePerform(MemoryCell cell, BrainfuckRunner runner, BrainfuckCommand cmd) {
        if (!(cmd instanceof BrainFCommand)) {
            return
        }
        BrainFCommand command = (BrainFCommand) cmd
        switch (command) {
            case BrainFCommand.ADD:
            case BrainFCommand.SUBTRACT:
            case BrainFCommand.READ:
                cell.data(this, ReadWriteData).writeCount++
                maxMemory = Math.max(maxMemory, runner.memory.memoryIndex)
                break
            case BrainFCommand.WHILE:
            case BrainFCommand.END_WHILE:
            case BrainFCommand.WRITE:
                cell.data(this, ReadWriteData).readCount++
                maxMemory = Math.max(maxMemory, runner.memory.memoryIndex)
                break
        }
    }

    @Override
    void print() {
        println "Total memory used = $cellsUsed"
    }

}

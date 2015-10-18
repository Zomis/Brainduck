package net.zomis.brainf.analyze.analyzers

import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.analyze.CellTagger
import net.zomis.brainf.analyze.IndexCounter
import net.zomis.brainf.analyze.IndexCounters
import net.zomis.brainf.analyze.MemoryCell
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.classic.BrainFCommand

import java.util.function.Function
import java.util.stream.Stream

class WhileLoopAnalysis implements BrainfuckAnalyzer {

    public static class CellLoops implements CellTagger {
        IndexCounter whileLoopStart = new IndexCounter('loop-begin')
        IndexCounter whileLoopContinue = new IndexCounter('loop-continue')
        IndexCounter whileLoopEnd = new IndexCounter('loop-end')

        @Override
        Stream<String> tags(Function<Integer, String> indexToStringFunction) {
            Stream.of(whileLoopStart, whileLoopContinue, whileLoopEnd)
                .flatMap({it.tags(indexToStringFunction)})
        }

        String toString() { 'CellLoops ' }
    }

    private final IndexCounters whileLoopCounts = new IndexCounters()

    IndexCounters getWhileLoopCounts() {
        return this.whileLoopCounts
    }

    @Override
    Object createMemoryData() {
        return new CellLoops()
    }

    @Override
    void print() {
        println 'While loops analysis'
        whileLoopCounts.sorted().forEach({entry ->
            println "$entry.key $entry.value"
        })
        println()
    }

    @Override
    void beforePerform(MemoryCell cell, BrainfuckRunner runner, BrainfuckCommand command) {
        int codeIndex = runner.code.commandIndex
        if (command == BrainFCommand.WHILE) {
            IndexCounter counter = whileLoopCounts.getOrCreate(runner.code.commandIndex)
            int current = runner.memory.value
            if (current == 0) {
                counter.add(0)
            } else {
                whileLoopCounts.begin(counter)
                cell.data(this, CellLoops).whileLoopStart.add(codeIndex)
            }
        }

        if (command == BrainFCommand.END_WHILE) {
            whileLoopCounts.recent().increase()
            int startIndex = whileLoopCounts.recent().forIndex
            int current = runner.memory.value
            if (current == 0) {
                whileLoopCounts.finishLast()
                cell.data(this, CellLoops).whileLoopEnd.add(startIndex)
            } else {
                cell.data(this, CellLoops).whileLoopContinue.add(startIndex)
            }
        }
    }
}

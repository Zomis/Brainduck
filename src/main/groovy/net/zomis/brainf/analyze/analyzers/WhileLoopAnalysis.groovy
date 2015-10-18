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
    private int bracketsOpened
    private int bracketsClosed

    IndexCounters getWhileLoopCounts() {
        return this.whileLoopCounts
    }

    @Override
    Object createMemoryData() {
        return new CellLoops()
    }

    @Override
    void beforeStart(BrainfuckRunner runner) {
        for (int i = 0; i < runner.code.commandCount; i++) {
            BrainfuckCommand command = runner.code.getCommandAt(i)
            if (command == BrainFCommand.WHILE) {
                bracketsOpened++
            }
            if (command == BrainFCommand.END_WHILE) {
                bracketsClosed++
            }
        }
    }

    @Override
    void print() {
        println 'While loops analysis'
        if (bracketsOpened != bracketsClosed) {
            println "ERROR: Number of starting brackets ($bracketsOpened) mismatch number of ending brackets ($bracketsClosed)"
        }
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
            IndexCounter recent = whileLoopCounts.recent()
            if (recent == null) {
                return
            }
            recent.increase()
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

    int getBracketsOpened() {
        this.@bracketsOpened
    }

    int getBracketsClosed() {
        this.@bracketsClosed
    }

    boolean isBracketsMatching() {
        this.bracketsClosed == this.bracketsOpened
    }

}

package net.zomis.brainf.analyze.analyzers

import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.analyze.CellTagger
import net.zomis.brainf.analyze.IndexCounter
import net.zomis.brainf.analyze.MemoryCell
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.classic.BrainFCommand

import java.util.function.Function
import java.util.stream.Stream

class IOAnalysis implements BrainfuckAnalyzer {

    public static class CellIO implements CellTagger {
        IndexCounter prints = new IndexCounter('print')
        IndexCounter userInputs = new IndexCounter('userInput')

        @Override
        Stream<String> tags(Function<Integer, String> indexToStringFunction) {
            return Stream.concat(prints.tags(indexToStringFunction), userInputs.tags(indexToStringFunction))
        }

        @Override
        String toString() { 'CellIO ' }
    }

    @Override
    Object createMemoryData() {
        return new CellIO()
    }

    @Override
    void beforePerform(MemoryCell cell, BrainfuckRunner runner, BrainfuckCommand command) {
        int codeIndex = runner.code.commandIndex
        if (command == BrainFCommand.READ) {
            cell.data(this, CellIO).userInputs.add(codeIndex)
        }

        if (command == BrainFCommand.WRITE) {
            cell.data(this, CellIO).prints.add(codeIndex)
        }

    }
}

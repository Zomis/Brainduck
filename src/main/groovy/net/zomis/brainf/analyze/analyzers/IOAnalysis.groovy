package net.zomis.brainf.analyze.analyzers

import groovy.transform.CompileStatic
import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.analyze.CellTagger
import net.zomis.brainf.analyze.IndexCounter
import net.zomis.brainf.analyze.MemoryCell
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ast.tree.PrintSyntax
import net.zomis.brainf.model.ast.tree.ReadSyntax
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.classic.BrainFCommand

import java.util.function.Function
import java.util.stream.Stream

@CompileStatic
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
    void beforePerform(MemoryCell cell, BrainfuckRunner runner, Syntax command) {
        int codeIndex = runner.code.commandIndex
        if (command instanceof ReadSyntax) {
            cell.data(this, CellIO).userInputs.add(codeIndex)
        }

        if (command instanceof PrintSyntax) {
            cell.data(this, CellIO).prints.add(codeIndex)
        }

    }
}

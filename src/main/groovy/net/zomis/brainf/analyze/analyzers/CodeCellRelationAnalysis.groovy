package net.zomis.brainf.analyze.analyzers

import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.analyze.MemoryCell
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ast.BFToken
import net.zomis.brainf.model.ast.Token
import net.zomis.brainf.model.ast.tree.ChangePointerSyntax
import net.zomis.brainf.model.ast.tree.ChangeValueSyntax
import net.zomis.brainf.model.ast.tree.CommentSyntax
import net.zomis.brainf.model.ast.tree.SteppableSyntax
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.classic.BrainFCommand

class CodeCellRelationAnalysis implements BrainfuckAnalyzer {

    Map<Integer, Set<Integer>> codeToCells = new HashMap<>()
    Map<Integer, Set<Integer>> cellsToCode = new HashMap<>()

    static void add(Map<Integer, Set<Integer>> map, int key, int value) {
        map.putIfAbsent(key, new HashSet<Integer>())
        map.get(key).add(value)
    }

    @Override
    Object createMemoryData() {
        return null // new CodeCellRelationship()
    }

    @Override
    void beforeStart(BrainfuckRunner runner) {
    }

    @Override
    void after(Brainalyze analyze, BrainfuckRunner runner) {
    }

    @Override
    void print() {
/*        println 'Code to cells'
        codeToCells.forEach({key, value ->
            println "$key --> $value"
        })
        println ''
        println 'Cells to code'
        cellsToCode.forEach({key, value ->
            println "$key --> $value"
        })
        println ''*/
    }

    @Override
    void beforePerform(MemoryCell cell, BrainfuckRunner runner, Syntax command) {
        if (command instanceof CommentSyntax) {
            return
        }
        int codeIndex = runner.code.commandIndex
        int cellIndex = runner.memory.memoryIndex

        if (command instanceof ChangeValueSyntax) {
            for (Token token : command.getTokens()) {
                if (token instanceof BFToken) {
                    codeIndex = token.info.position
                    add(cellsToCode, cellIndex, codeIndex)
                    add(codeToCells, codeIndex, cellIndex)
                }
            }
        } else if (command instanceof ChangePointerSyntax) {
            // '<< << << <<' should be one command
            int offset = 0;
            for (Token token : command.getTokens()) {
                if (token instanceof BFToken) {
                    codeIndex = token.info.position
                    int diff = (token as BFToken).command == BrainFCommand.NEXT ? 1 : -1;
                    add(cellsToCode, cellIndex + offset, codeIndex)
                    add(codeToCells, codeIndex, cellIndex + offset)
                    offset += diff
                }
            }
        } else {
            add(cellsToCode, cellIndex, codeIndex)
            add(codeToCells, codeIndex, cellIndex)
        }
    }

    @Override
    void afterPerform(MemoryCell cell, BrainfuckRunner runner, Syntax command) {
    }

    Set<Integer> codeAccessedBy(Iterable<? extends Integer> indexes) {
        Set<Integer> result = new HashSet<>()
        for (Integer idx : indexes) {
            result.addAll(cellsToCode.getOrDefault(idx, Collections.emptySet()))
        }
        result
    }

}

package net.zomis.brainf.analyze;

import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ast.tree.Syntax

interface BrainfuckAnalyzer {

    fun createMemoryData(): Any {
        return Unit
    }
    fun beforeStart(runner: BrainfuckRunner) { }
    fun after(analyze: Brainalyze, runner: BrainfuckRunner) { }
    fun print() { }
    fun beforePerform(cell: MemoryCell, runner: BrainfuckRunner, command: Syntax) { }
    fun afterPerform(cell: MemoryCell, runner: BrainfuckRunner, command: Syntax) { }

    fun beforeWhile(cell: MemoryCell, runner: BrainfuckRunner) {}
    fun afterWhile(cell: MemoryCell, runner: BrainfuckRunner) {}
    fun beforeEndWhile(cell: MemoryCell, runner: BrainfuckRunner) {}
    fun afterEndWhile(cell: MemoryCell, runner: BrainfuckRunner) {}

}

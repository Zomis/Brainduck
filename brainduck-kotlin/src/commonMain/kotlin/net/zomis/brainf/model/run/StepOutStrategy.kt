package net.zomis.brainf.model.run

import net.zomis.brainf.model.ast.tree.SyntaxTree
import net.zomis.brainf.model.BrainfuckRunner

class StepOutStrategy : RunStrategy {

    private var enteredSyntaxes: Int = 0
    private lateinit var activeTree: SyntaxTree

    override fun start(runner: BrainfuckRunner): Boolean {
        enteredSyntaxes = runner.code.enteredTrees.size
        activeTree = runner.code.currentTree.tree
        if (runner.code.currentSyntax is SyntaxTree) {
            return false
        }
        return enteredSyntaxes > 1
    }

    override fun next(runner: BrainfuckRunner): Boolean {
        runner.runSyntax()
        if (runner.code.currentSyntax == activeTree) {
            return true
        }
        return runner.code.enteredTrees.size >= enteredSyntaxes
    }

}

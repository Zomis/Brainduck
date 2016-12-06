package net.zomis.brainf.model.run

import net.zomis.brainf.model.ast.tree.SyntaxTree
import net.zomis.brainf.model.BrainfuckRunner

class StepOutStrategy implements RunStrategy {

    private int enteredSyntaxes
    private SyntaxTree activeTree

    @Override
    boolean start(BrainfuckRunner runner) {
        enteredSyntaxes = runner.code.enteredTrees.size()
        activeTree = runner.code.currentTree.tree
        if (runner.code.currentSyntax instanceof SyntaxTree) {
            return false
        }
        return enteredSyntaxes > 1
    }

    @Override
    boolean next(BrainfuckRunner runner) {
        runner.runSyntax()
        if (runner.code.currentSyntax == activeTree) {
            return true
        }
        return runner.code.enteredTrees.size() >= enteredSyntaxes
    }

}

package net.zomis.brainf.model.run

import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.ast.tree.SyntaxTree
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner

/**
 * Strategy for going to the first code after the next EndWhile.
 * If no EndWhile exists, go to the next While.
 * If also no While exists, run a single step.
 */
class StepContinueStrategy : RunStrategy {

    private var startingEnteredTreesCount: Int = 0
    private var currentLoop: SyntaxTree? = null
    private var nextLoop: SyntaxTree? = null

    private var performedOnce: Boolean = false
    private val singleStep = LimitedStepsStrategy()

    override fun start(runner: BrainfuckRunner): Boolean {
        startingEnteredTreesCount = runner.code.enteredTrees.size
        if (!runner.isOnRootTree()) {
            currentLoop = runner.code.currentTree.tree
        } else if (runner.code.currentSyntax is SyntaxTree) {
            currentLoop = runner.code.currentSyntax as SyntaxTree
        }
        val pos = runner.code.currentTree.iteratorCopy()
        while (pos.hasNext()) {
            val syntax = pos.next()
            if (syntax is SyntaxTree) {
                nextLoop = syntax
                break;
            }
        }
        performedOnce = false
        println("loopStart with $currentLoop and $nextLoop")

        return singleStep.start(runner)
    }

    override fun next(runner: BrainfuckRunner): Boolean {
        if (runner.code.isFinished()) {
            return false
        }

        if (currentLoop != null) {
            // if inside loop, either end the loop or go to beginning of loop
            val loopHasEnded = runner.code.enteredTrees.size < startingEnteredTreesCount
            val startOfLoop = runner.code.currentTree.tree == this.currentLoop &&
                    runner.code.currentTree.currentIndex == 0
            var perform = !loopHasEnded && !startOfLoop
            if (!performedOnce) {
                perform = true
                performedOnce = !(runner.code.currentSyntax is SyntaxTree) // Perform again if started on WHILE
            }
            if (perform) {
                runner.runSyntax()
            }
            return perform
        } else if (nextLoop != null) {
            // Not inside loop, go to next starting loop if there is one
            if (runner.code.currentSyntax != nextLoop) {
                runner.runSyntax()
                return true
            }
            return false
        } else {
            // if not inside loop and no next loop, do the same as `StepNext`
            return singleStep.next(runner)
        }
    }

}

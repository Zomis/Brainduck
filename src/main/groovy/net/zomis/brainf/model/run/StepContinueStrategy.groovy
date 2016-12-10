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
class StepContinueStrategy implements RunStrategy {

    private int startingEnteredTreesCount;
    private SyntaxTree currentLoop;
    private SyntaxTree nextLoop;

    private boolean performedOnce
    private final RunStrategy singleStep = new LimitedStepsStrategy()

    @Override
    boolean start(BrainfuckRunner runner) {
        startingEnteredTreesCount = runner.code.enteredTrees.size()
        if (!runner.isOnRootTree()) {
            currentLoop = runner.code.getCurrentTree().tree
        } else if (runner.code.currentSyntax instanceof SyntaxTree) {
            currentLoop = runner.code.currentSyntax as SyntaxTree
        }
        def pos = runner.code.getCurrentTree().iteratorCopy();
        while (pos.hasNext()) {
            Syntax syntax = pos.next();
            if (syntax instanceof SyntaxTree) {
                nextLoop = syntax;
                break;
            }
        }
        performedOnce = false
        println 'loopStart with ' + currentLoop + ' and ' + nextLoop

        return singleStep.start(runner)
    }

    @Override
    boolean next(BrainfuckRunner runner) {
        if (runner.code.isFinished()) {
            return false
        }

        if (currentLoop) {
            // if inside loop, either end the loop or go to beginning of loop
            boolean loopHasEnded = runner.code.enteredTrees.size() < startingEnteredTreesCount
            boolean startOfLoop = runner.code.currentTree.tree == this.currentLoop &&
                    runner.code.currentTree.currentIndex == 0
            boolean perform = !loopHasEnded && !startOfLoop
            if (!performedOnce) {
                perform = true
                performedOnce = !(runner.code.currentSyntax instanceof SyntaxTree) // Perform again if started on WHILE
            }
            if (perform) {
                runner.runSyntax()
            }
            return perform
        } else if (nextLoop) {
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

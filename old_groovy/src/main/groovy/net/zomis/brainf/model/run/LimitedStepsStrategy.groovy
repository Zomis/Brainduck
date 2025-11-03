package net.zomis.brainf.model.run

import net.zomis.brainf.model.ast.tree.CommentSyntax
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.classic.BrainFCommand
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner

class LimitedStepsStrategy implements RunStrategy {

    private int remaining
    private final int count

    LimitedStepsStrategy() {
        this(1)
    }

    LimitedStepsStrategy(int count) {
        this.count = count
    }

    @Override
    boolean start(BrainfuckRunner runner) {
        remaining = count
        return true
    }

    @Override
    boolean next(BrainfuckRunner runner) {
        if (remaining > 0) {
            Syntax comm = runner.step();
            if (!isSkipSyntax(comm)) {
                remaining--
                System.out.println("Step: " + comm);
            }
            return true
        } else {
            Syntax comm = runner.code.currentSyntax
            if (isSkipSyntax(comm)) {
                runner.step()
            }
            return isSkipSyntax(comm)
        }
    }

    static boolean isSkipSyntax(Syntax syntax) {
        return syntax instanceof CommentSyntax
    }

}

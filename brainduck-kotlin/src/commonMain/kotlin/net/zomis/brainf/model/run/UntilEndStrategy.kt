package net.zomis.brainf.model.run

import net.zomis.brainf.model.BrainfuckRunner

class UntilEndStrategy : RunStrategy {

    override fun start(runner: BrainfuckRunner): Boolean = true

    override fun next(runner: BrainfuckRunner): Boolean {
        if (runner.code.isFinished()) {
            return false
        }
        runner.runSyntax()
        return !runner.code.isFinished()
    }

}

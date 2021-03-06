package net.zomis.brainf.model.run

import groovy.transform.CompileStatic
import net.zomis.brainf.model.BrainfuckRunner

@CompileStatic
class UntilEndStrategy implements RunStrategy {

    @Override
    boolean start(BrainfuckRunner runner) {
        return true
    }

    @Override
    boolean next(BrainfuckRunner runner) {
        if (runner.code.isFinished()) {
            return false;
        }
        runner.runSyntax()
        return !runner.code.isFinished()
    }

}

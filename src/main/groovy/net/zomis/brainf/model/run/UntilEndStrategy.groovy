package net.zomis.brainf.model.run

import net.zomis.brainf.model.BrainfuckRunner

class UntilEndStrategy implements RunStrategy {

    @Override
    boolean start(BrainfuckRunner runner) {
        return true
    }

    @Override
    boolean next(BrainfuckRunner runner) {
        return runner.step() != null
    }

}

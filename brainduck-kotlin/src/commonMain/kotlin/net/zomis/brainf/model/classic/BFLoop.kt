package net.zomis.brainf.model.classic;

import net.zomis.brainf.model.BrainfuckCommand;
import net.zomis.brainf.model.BrainfuckRunner;

@Deprecated("")
class BFLoop(val i: Int) : BrainfuckCommand {

    override fun perform(runner: BrainfuckRunner) {
        if (i == 1) {
            if (runner.memory.value == 0) {
                throw UnsupportedOperationException();
            }
        } else if (i == -1) {
            if (runner.memory.value != 0) {
                throw UnsupportedOperationException();
            }
        } else {
            throw IllegalStateException("Invalid i: $i");
        }
    }
}

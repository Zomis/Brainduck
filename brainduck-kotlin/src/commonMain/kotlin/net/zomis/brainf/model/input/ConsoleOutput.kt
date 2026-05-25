package net.zomis.brainf.model.input;

import net.zomis.brainf.model.BrainfuckOutput

class ConsoleOutput : BrainfuckOutput {
    override fun write(value: Char) {
        print(value)
    }
}

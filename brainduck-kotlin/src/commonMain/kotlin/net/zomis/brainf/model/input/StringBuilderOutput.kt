package net.zomis.brainf.model.input

import net.zomis.brainf.model.BrainfuckOutput

class StringBuilderOutput : BrainfuckOutput {

    private val output = StringBuilder()

    override fun write(value: Char) {
        output.append(value)
    }

    override fun toString(): String = output.toString()

}

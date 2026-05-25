package net.zomis.brainf.model.input

import net.zomis.brainduck.Input

class FixedInput(val text: String) : Input {

    private var pos = 0

    override fun read(): Int {
        return text[pos++].toInt()
    }

}

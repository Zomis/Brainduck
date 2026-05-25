package net.zomis.brainf.model.input;

import net.zomis.brainduck.Input

class NoInput : Input {

    override fun read(): Int {
        throw NoSuchElementException()
    }

}

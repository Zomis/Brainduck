package net.zomis.brainf.model.input;

import net.zomis.brainduck.Input

class QueueInput(
    val iterator: Iterator<Int>
) : Input {

    override fun read(): Int {
        return iterator.next()
    }
}

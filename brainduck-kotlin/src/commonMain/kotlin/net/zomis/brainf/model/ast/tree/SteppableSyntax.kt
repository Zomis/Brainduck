package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;
import kotlin.math.absoluteValue

interface SteppableSyntax {

    fun getValue(): Int
    fun performTimes(runner: BrainfuckRunner, steps: Int)
    val times get() = getValue().absoluteValue

}

package net.zomis.brainf.model.run

import net.zomis.brainf.model.BrainfuckRunner

interface RunStrategy {

    fun start(runner: BrainfuckRunner): Boolean
    fun next(runner: BrainfuckRunner): Boolean

}

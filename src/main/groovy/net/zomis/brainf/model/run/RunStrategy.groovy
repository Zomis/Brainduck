package net.zomis.brainf.model.run

import net.zomis.brainf.model.BrainfuckRunner

interface RunStrategy {

    boolean start(BrainfuckRunner runner)
    boolean next(BrainfuckRunner runner)

}
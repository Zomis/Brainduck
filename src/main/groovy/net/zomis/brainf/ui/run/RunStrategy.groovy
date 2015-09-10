package net.zomis.brainf.ui.run

import net.zomis.brainf.model.BrainfuckRunner

interface RunStrategy {

    boolean next(BrainfuckRunner runner)

}
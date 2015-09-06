package net.zomis.brainf.model

import net.zomis.brainf.BrainFCommand

interface BrainfuckListener {

    void beforePerform(BrainfuckRunner runner, BrainFCommand command)

    void afterPerform(BrainfuckRunner runner, BrainFCommand command)

}

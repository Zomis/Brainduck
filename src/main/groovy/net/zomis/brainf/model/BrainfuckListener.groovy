package net.zomis.brainf.model

interface BrainfuckListener {

    void beforePerform(BrainfuckRunner runner, BrainfuckCommand command)

    void afterPerform(BrainfuckRunner runner, BrainfuckCommand command)

}

package net.zomis.brainf.model;

@Deprecated("")
fun interface BrainfuckCommand {

    fun perform(runner: BrainfuckRunner)

}

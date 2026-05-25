package net.zomis.brainf.model;

@Deprecated("")
interface CodeRetriever {

    fun getCommand(commandIndex: Int): BrainfuckCommand?

    fun getCommandLength(commandIndex: Int): Int = 1

    fun capacity(): Int
}

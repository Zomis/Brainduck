package net.zomis.brainf.model

interface BrainfuckCodeConverter {

    fun convert(code: String, add: (BrainfuckCommand) -> Unit)

}

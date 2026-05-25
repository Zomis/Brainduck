package net.zomis.brainf.model.classic

import net.zomis.brainf.model.BrainfuckCodeConverter
import net.zomis.brainf.model.BrainfuckCommand

class BrainfuckConverter : BrainfuckCodeConverter {

    override fun convert(code: String, add: (BrainfuckCommand) -> Unit) {
        code.toCharArray()
            .map { i -> BrainFCommand.getCommand(i) }
            .forEach(add)
    }

}

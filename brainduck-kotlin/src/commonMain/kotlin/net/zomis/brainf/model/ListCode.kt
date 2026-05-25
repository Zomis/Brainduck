package net.zomis.brainf.model

import net.zomis.brainf.model.groovy.GroovyBFContext
import net.zomis.brainf.model.groovy.GroovySupportConverter

class ListCode(
    private val commands: MutableList<BrainfuckCommand>,
    private val converter: BrainfuckCodeConverter
) : CodeRetriever {

    override fun getCommand(commandIndex: Int): BrainfuckCommand? {
        if (commandIndex < 0 || commandIndex >= commands.size) {
            return null
        }
        return commands[commandIndex]
    }

    override fun capacity(): Int = commands.size

    companion object {
//        static GroovySupportConverter newGroovyConverter() {
//            return new GroovySupportConverter(new GroovyBFContext(), new BrainfuckConverter())
//        }

//        fun create(code: String): ListCode {
//            return create(newGroovyConverter(), code)
//        }

        fun create(converter: BrainfuckCodeConverter, code: String): ListCode {
            return ListCode(mutableListOf(), converter).addCommands(code)
        }
    }

    fun addCommands(string: String): ListCode {
        converter.convert(string) { commands.add(it) }
        return this
    }

}

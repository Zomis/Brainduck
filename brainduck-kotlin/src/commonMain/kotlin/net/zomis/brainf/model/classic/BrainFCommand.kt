package net.zomis.brainf.model.classic;

import net.zomis.brainf.model.BrainfuckCommand;
import net.zomis.brainf.model.BrainfuckRunner;

@Deprecated("")
enum class BrainFCommand(
    val ch: Char,
    private val perform: BrainfuckCommand
) : BrainfuckCommand {

	NONE(0.toChar(), {}),
    NEXT('>', { r ->
        r.memory.setMemoryIndex(r.memory.memoryIndex + 1)
    }),
    PREVIOUS('<', { r ->
        r.memory.setMemoryIndex(r.memory.memoryIndex - 1)
    }),
    WRITE('.', { r ->
        val write = r.memory.value.toChar()
        r.appendOutput(write)
    }),
    READ(',', { r ->
        r.memory.setMemory(r.input.read())
    }),
    ADD('+', { r -> r.memory.changeMemory(1) }),
    SUBTRACT('-', { r -> r.memory.changeMemory(-1) }),
    WHILE('[', BFLoop(1)),
    END_WHILE(']', BFLoop(-1));

    fun isLoop() = this == WHILE || this == END_WHILE

    override fun perform(runner: BrainfuckRunner) {
        this.perform.perform(runner);
    }

    companion object {
        private val commands: Map<Char, BrainFCommand> = BrainFCommand.values().associateBy { it.ch }

        fun getCommand(ch: Char): BrainFCommand {
            return commands.getOrElse(ch) { NONE }
        }
    }

}

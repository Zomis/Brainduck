package net.zomis.brainduck.runner

import net.zomis.brainduck.BrainfuckInput
import net.zomis.brainduck.BrainfuckOutput
import net.zomis.brainduck.BrainfuckProgram

object LoopStartRunner : Runner {

    override fun run(
        program: BrainfuckProgram,
        input: BrainfuckInput,
        output: BrainfuckOutput,
        listeners: List<BrainfuckListener>
    ) {
        val current = program.syntaxPositions()

        while (!program.isFinished()) {
            // Run at least once, we don't want a no-op
            program.runSyntax(input, output, listeners)
            val pos = program.syntaxPositions()
            if (pos.size > current.size || pos.last() == 0) {
                break
            }
        }
    }

}
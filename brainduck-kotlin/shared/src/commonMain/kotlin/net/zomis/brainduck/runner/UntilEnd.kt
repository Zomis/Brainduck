package net.zomis.brainduck.runner

import net.zomis.brainduck.BrainfuckInput
import net.zomis.brainduck.BrainfuckOutput
import net.zomis.brainduck.BrainfuckProgram

object UntilEnd : Runner {
    override suspend fun run(
        program: BrainfuckProgram,
        input: BrainfuckInput,
        output: BrainfuckOutput,
        listeners: List<BrainfuckListener>,
        yielder: suspend () -> Unit,
    ) {
        while (!program.isFinished()) {
            program.runSyntax(input, output, listeners)
            yielder()
        }
    }
}
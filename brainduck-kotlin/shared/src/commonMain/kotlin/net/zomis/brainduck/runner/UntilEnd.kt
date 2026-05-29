package net.zomis.brainduck.runner

import kotlinx.coroutines.yield
import net.zomis.brainduck.BrainfuckInput
import net.zomis.brainduck.BrainfuckOutput
import net.zomis.brainduck.BrainfuckProgram

object UntilEnd : Runner {
    override suspend fun run(
        program: BrainfuckProgram,
        input: BrainfuckInput,
        output: BrainfuckOutput,
        listeners: List<BrainfuckListener>
    ) {
        while (!program.isFinished()) {
            program.runSyntax(input, output, listeners)
            yield()
        }
    }
}
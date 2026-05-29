package net.zomis.brainduck.runner

import kotlinx.coroutines.yield
import net.zomis.brainduck.BrainfuckInput
import net.zomis.brainduck.BrainfuckOutput
import net.zomis.brainduck.BrainfuckProgram

class StepRunner(val steps: Int = 1) : Runner {
    init {
        require(steps > 0)
    }

    override suspend fun run(
        program: BrainfuckProgram,
        input: BrainfuckInput,
        output: BrainfuckOutput,
        listeners: List<BrainfuckListener>
    ) {
        repeat(steps) {
            if (!program.isFinished()) program.runSyntax(input, output, listeners)
            yield()
        }
    }
}
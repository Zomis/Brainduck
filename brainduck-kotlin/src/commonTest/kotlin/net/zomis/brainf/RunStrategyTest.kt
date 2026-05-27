package net.zomis.brainf

import kotlinx.coroutines.withTimeout
import net.zomis.brainduck.Brainfuck
import net.zomis.brainduck.BrainfuckInput
import net.zomis.brainduck.BrainfuckOutput
import net.zomis.brainduck.ast.SyntaxData
import net.zomis.brainduck.runner.UntilEnd
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds

class RunStrategyTest {

    private val code = Brainfuck.code("+++++[->+<]>+++")

    @Test
    fun testTree() {
        val tree = code.syntax
        assertEquals(4, tree.children.size)
        assertEquals(5, (tree.children[0].data as SyntaxData.ChangeValue).delta)
        assertEquals(5, (tree.children[1].data as SyntaxData.WhileNotZero).children.size)
        val nested = tree.children[1].data as SyntaxData.WhileNotZero
        assertEquals(-1, (nested.children[0].data as SyntaxData.ChangeValue).delta)
        assertEquals(1, (nested.children[1].data as SyntaxData.Move).delta)
        assertEquals(1, (nested.children[2].data as SyntaxData.ChangeValue).delta)
        assertEquals(-1, (nested.children[3].data as SyntaxData.Move).delta)
        assertEquals(SyntaxData.EndWhile, nested.children[4].data)
        assertEquals(1, (tree.children[2].data as SyntaxData.Move).delta)
        assertEquals(3, (tree.children[3].data as SyntaxData.ChangeValue).delta)
    }

    @Test
    fun singleStep() {
//        brain.run(new LimitedStepsStrategy())
//        assert brain.memory.getMemoryArray(0, 2) == [1, 0] as int[]
    }

    @Test
    fun twoSteps() {
//        brain.run(new LimitedStepsStrategy(2))
//        assert brain.memory.getMemoryArray(0, 2) == [2, 0] as int[]
    }

    @Test
    fun loopStart() {
//        withTimeout(2000.milliseconds) {
//        brain.run(new RunUntilLoopStartStrategy())
//        assert brain.memory.getMemoryArray(0, 2) == [5, 0] as int[]
//        assert brain.code.currentSyntax instanceof LoopInstructionSyntax
//        }
    }

    @Test
    fun untilEndStrategy() {
        val program = code.createProgram()
        assertEquals(4, code.syntax.children.size)
        UntilEnd.run(program, BrainfuckInput.NoInput, BrainfuckOutput.NoOutput, emptyList())
        assertEquals(listOf(0, 8), program.memory.get(0..1))
    }

    /*
    * TODO: Add test cases:
    * - Dynamically change currentSyntax and run from there (run loop x times)
    * - Dynamically modify tree and run
    * - Step Continue
    * - Step Out
    **/

}

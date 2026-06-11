package net.zomis.brainf

import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.yield
import net.zomis.brainduck.Brainfuck
import net.zomis.brainduck.BrainfuckInput
import net.zomis.brainduck.BrainfuckOutput
import net.zomis.brainduck.ast.SyntaxData
import net.zomis.brainduck.runner.UntilEnd
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

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
    fun singleSyntaxStep() = runTest {
        val program = code.createProgram()
        program.run(
            Brainfuck.Run.stepSyntax,
            BrainfuckInput.NoInput, BrainfuckOutput.NoOutput
        )
        assertEquals(listOf(5, 0), program.memory.get(0..1))
    }

    @Test
    @Ignore
    fun singleStep() = runTest {
        val program = code.createProgram()
        program.run(
            Brainfuck.Run.step,
            BrainfuckInput.NoInput, BrainfuckOutput.NoOutput
        )
        assertEquals(listOf(1, 0), program.memory.get(0..1))
    }

    @Test
    @Ignore
    fun twoSteps() = runTest {
        val program = code.createProgram()
        program.run(
            Brainfuck.Run.step,
            BrainfuckInput.NoInput, BrainfuckOutput.NoOutput
        )
        assertEquals(listOf(2, 0), program.memory.get(0..1))
    }

    @Test
    fun loopStart() = runTest {
        val program = code.createProgram()
        program.run(Brainfuck.Run.loopStart, BrainfuckInput.NoInput, BrainfuckOutput.NoOutput)
        assertEquals(listOf(5, 0), program.memory.get(0..1))
        assertEquals(-1, (program.currentSyntax().data as SyntaxData.ChangeValue).delta)
    }

    @Test
    fun untilEndStrategy() = runTest {
        val program = code.createProgram()
        assertEquals(4, code.syntax.children.size)
        UntilEnd.run(program, BrainfuckInput.NoInput, BrainfuckOutput.NoOutput, emptyList(), { yield() })
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

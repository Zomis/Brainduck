package net.zomis.brainf

import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.model.BrainfuckMemory
import net.zomis.brainf.model.BrainfuckRunner
import org.junit.Test

import static org.junit.Assert.assertArrayEquals
import static org.junit.Assert.assertEquals
import static org.junit.Assert.fail

public class BrainTest {

    @Test
    public void gotoCorrectEndWhile() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.getCode().addCommands(">+>[-]+   ");
        brain.getCode().addCommands("++[-->++]-->   Find next 254 and go one step beyond it");
        brain.getCode().addCommands("            Loop through all 254s");
        brain.getCode().addCommands("+++[---         Make sure that we are not at 253 (end)");
        brain.getCode().addCommands("++[--<++]--	");

        assertEquals(BrainFCommand.NEXT, brain.step());
        assertEquals(BrainFCommand.ADD, brain.step());
        assertEquals(BrainFCommand.NEXT, brain.step());
        assertEquals(BrainFCommand.WHILE, brain.step());

        assertEquals(6, brain.getCode().getCommandIndex());
        assertEquals(BrainFCommand.ADD, brain.step());
    }

    @Test
    public void simpleLoopMultiplication() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.getCode().addCommands("++[>+++<-]>>>");
        brain.run();
        assertArrayEquals([ 0, 6, 0, 0, 0, 0, 0, 0, 0, 0 ] as byte[],
                brain.getMemory().getMemoryArray(0, 10));
    }

    @Test
    public void analyzeLoops() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.getCode().addCommands("++[ > +++++[>+>+++<<-]>[>+<-]<[+-+-]> +++ << -]");
        Brainalyze analyze = Brainalyze.analyze(brain);
        Map<Integer, List<Integer>> counts = analyze.getWhileLoopCounts();
        assert counts.size() == 4
        assert counts[2] == [2]
        assert counts[11] == [5, 5]
        assert counts[23] == [5, 8]
        assert counts[30] == [0, 0]
    }

    @Test
    public void printAlphabet() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.getCode().addCommands("++++++[>++++++++++>++++<<-]>+++++>++[-<.+>]");
        brain.run();
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", brain.getOutput());
    }

    @Test
    public void input() {
        BrainfuckRunner brain = BrainF.createFromCodeAndInput(BrainfuckMemory.DEFAULT_MEMORY_SIZE, "+++,.", "a");
        brain.run();
        assertEquals("a", brain.getOutput());
    }

    @Test
    public void simpleCommands() {
        BrainfuckRunner abc = BrainF.createWithDefaultSize();
        abc.getCode().addCommands("+>++>+++<");
        abc.run();
        assertEquals(9, abc.getCode().getCommandIndex());
        assertEquals(1, abc.getMemory().getMemoryIndex());
        assertEquals(2, abc.getMemory().getMemory());
        abc.perform(BrainFCommand.PREVIOUS);
        assertEquals(1, abc.getMemory().getMemory());
        abc.perform(BrainFCommand.NEXT);
        abc.perform(BrainFCommand.NEXT);
        assertEquals(3, abc.getMemory().getMemory());

    }

}

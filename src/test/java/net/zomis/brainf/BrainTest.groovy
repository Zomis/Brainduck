package net.zomis.brainf

import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.model.BrainfuckMemory
import net.zomis.brainf.model.BrainfuckRunner
import org.junit.Test

public class BrainTest {

    @Test
    public void gotoCorrectEndWhile() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.getCode().addCommands(">+>[-]+   ");
        brain.getCode().addCommands("++[-->++]-->   Find next 254 and go one step beyond it");
        brain.getCode().addCommands("            Loop through all 254s");
        brain.getCode().addCommands("+++[---         Make sure that we are not at 253 (end)");
        brain.getCode().addCommands("++[--<++]--	");

        assert brain.step() == BrainFCommand.NEXT
        assert brain.step() == BrainFCommand.ADD
        assert brain.step() == BrainFCommand.NEXT
        assert brain.step() == BrainFCommand.WHILE

        assert brain.code.commandIndex == 6
        assert brain.step() == BrainFCommand.ADD
    }

    @Test
    public void simpleLoopMultiplication() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.getCode().addCommands("++[>+++<-]>>>");
        brain.run();
        assert [ 0, 6, 0, 0, 0, 0, 0, 0, 0, 0 ] == brain.getMemory().getMemoryArray(0, 10)
    }

    @Test
    public void analyzeLoops() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.getCode().addCommands("++[ > +++++[>+>+++<<-]>[>+<-]<[+-+-]> +++ << -]");
        Brainalyze analyze = Brainalyze.analyze(brain);
        analyze.print()
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
        assert brain.getOutput() == "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    }

    @Test
    public void fizzBuzz() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.getCode().addCommands(BrainfuckRunner.classLoader.getResource('fizzbuzz.bf').text);
        Brainalyze analyze = Brainalyze.analyze(brain)
        analyze.print()
    }

    @Test
    public void allCharacters() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize()
        brain.code.addCommands(">>>>+++++++++++++++[<+++++++++++++++++>-]<[->[+>>]+[<<]>]")
        Brainalyze analyze = Brainalyze.analyze(brain)
        analyze.print()
    }

    @Test
    public void input() {
        BrainfuckRunner brain = BrainF.createFromCodeAndInput(BrainfuckMemory.DEFAULT_MEMORY_SIZE, "+++,.", "a");
        brain.run();
        assert "a" == brain.getOutput()
    }

    @Test
    public void simpleCommands() {
        BrainfuckRunner abc = BrainF.createWithDefaultSize();
        abc.getCode().addCommands("+>++>+++<");
        abc.run();
        assert 9 == abc.getCode().getCommandIndex()
        assert 1 == abc.getMemory().getMemoryIndex()
        assert 2 == abc.getMemory().getMemory()
        abc.perform(BrainFCommand.PREVIOUS);
        assert 1 == abc.getMemory().getMemory()
        abc.perform(BrainFCommand.NEXT);
        abc.perform(BrainFCommand.NEXT);
        assert 3 == abc.getMemory().getMemory()
    }

}

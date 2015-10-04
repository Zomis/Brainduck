package net.zomis.brainf

import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.IndexCounters
import net.zomis.brainf.model.BrainF
import net.zomis.brainf.model.ListCode
import net.zomis.brainf.model.classic.BrainFCommand
import net.zomis.brainf.model.BrainfuckMemory
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.run.StepContinueStrategy
import net.zomis.brainf.model.run.StepOutStrategy
import org.junit.Test

public class BrainTest {

    @Test
    public void gotoCorrectEndWhile() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.code.source = ListCode.create(">+>[-]+   ")
            .addCommands("++[-->++]-->   Find next 254 and go one step beyond it")
            .addCommands("            Loop through all 254s")
            .addCommands("+++[---         Make sure that we are not at 253 (end)")
            .addCommands("++[--<++]--	");

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
        brain.code.source = ListCode.create("++[>+++<-]>>>");
        brain.run();
        assert [ 0, 6, 0, 0, 0, 0, 0, 0, 0, 0 ] == brain.getMemory().getMemoryArray(0, 10)
    }

    @Test
    public void analyzeLoops() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.code.source = ListCode.create("++[ > +++++[>+>+++<<-]>[>+<-]<[+-+-]> +++ << -]");
        Brainalyze analyze = Brainalyze.analyze(brain);
        IndexCounters counts = analyze.getWhileLoopCounts();
        assert counts.size() == 4
        assert counts[2] == [2]
        assert counts[11] == [5, 5]
        assert counts[23] == [5, 8]
        assert counts[30] == [0, 0]
    }

    @Test
    public void loopOnce() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.code.source = ListCode.create("+[-]");
        Brainalyze analyze = Brainalyze.analyze(brain);
        IndexCounters counts = analyze.getWhileLoopCounts();
        assert counts.size() == 1
        assert counts[1] == [1]
    }

    @Test
    public void printAlphabet() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.code.source = ListCode.create("++++++[>++++++++++>++++<<-]>+++++>++[-<.+>]");
        brain.run();
        assert brain.getOutput() == "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    }

    @Test
    public void fizzBuzz() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.code.source = ListCode.create(BrainfuckRunner.classLoader.getResource('fizzbuzz.bf').text);
        Brainalyze analyze = Brainalyze.analyze(brain)
        assert analyze.getActionsForCommand(BrainFCommand.WRITE) == brain.output.length()
        assert brain.output == fizzBuzzString(100)
    }

    @Test
    public void fizzBuzzMin() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.code.source = ListCode.create(BrainfuckRunner.classLoader.getResource('fizzbuzz-min.bf').text);
        Brainalyze analyze = Brainalyze.analyze(brain)
        assert analyze.getActionsForCommand(BrainFCommand.WRITE) == brain.output.length()
        assert brain.output == fizzBuzzString(100)
    }

    @Test
    public void includeTest() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.code.source = ListCode.create(BrainfuckRunner.classLoader.getResource('include-base.bf').text);
        brain.run()
        assert brain.memory.getMemoryArray(0, 5) == [0, 0, 12, 0, 0] as int[]
    }

    @Test
    public void stepContinueStrategy() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.code.source = ListCode.create("+++[>+<-]-");
        assert brain.step() == BrainFCommand.ADD
        assert brain.step() == BrainFCommand.ADD
        assert brain.step() == BrainFCommand.ADD
        assert brain.run(new StepContinueStrategy()) == 0
        assert brain.step() == BrainFCommand.WHILE
        int count = brain.run(new StepContinueStrategy())
        assert count == 5
        assert brain.memory.getMemory(1) == 1
        assert brain.code.nextCommand == BrainFCommand.NEXT
    }

    @Test
    public void stepOutStrategy() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize();
        brain.code.source = ListCode.create("+++[>+<-]-");
        assert brain.step() == BrainFCommand.ADD
        assert brain.step() == BrainFCommand.ADD
        assert brain.step() == BrainFCommand.ADD
        assert brain.run(new StepOutStrategy()) == 0
        assert brain.step() == BrainFCommand.WHILE
        assert brain.run(new StepOutStrategy()) == 15
        assert brain.memory.getMemory(1) == 3
        assert brain.code.nextCommand == BrainFCommand.SUBTRACT
    }

    static String fizzBuzzString(int max) {
        StringBuilder str = new StringBuilder()
        for (int i = 1; i <= max; i++) {
            if (i % 15 == 0) {
                str.append('FizzBuzz\n')
            } else if (i % 5 == 0) {
                str.append('Buzz\n')
            } else if (i % 3 == 0) {
                str.append('Fizz\n')
            } else {
                str.append("$i\n")
            }
        }
        str.toString()
    }

    @Test
    public void allCharacters() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize()
        brain.code.source = ListCode.create(">>>>+++++++++++++++[<+++++++++++++++++>-]<[->[+>>]+[<<]>]")
        Brainalyze analyze = Brainalyze.analyze(brain)
        analyze.print()
    }

    @Test
    public void readsAndWrites() {
        BrainfuckRunner brain = BrainF.createWithDefaultSize()
        brain.code.source = ListCode.create(">> +++++ [->[+>>]+[<<]>]")
        // distribute values from 5 downto 1 across the tape
        Brainalyze analyze = Brainalyze.analyze(brain)
        assert analyze.arrayLong({it.readCount}, 0, 12)  == [0, 5, 6, 10, 0, 8, 0, 6, 0, 4, 0, 2] as int[]
        assert analyze.arrayLong({it.writeCount}, 0, 12) == [0, 0, 10, 5, 0, 4, 0, 3, 0, 2, 0, 1] as int[]
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
        abc.code.source = ListCode.create("+>++>+++<");
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

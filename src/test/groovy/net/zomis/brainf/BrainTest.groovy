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
import org.junit.Before
import org.junit.Test

public class BrainTest {

    BrainfuckRunner brain
    ListCode source
    Brainalyze analyze

    void analyze() {
        analyze = Brainalyze.analyze(brain)
    }

    @Before
    public void setup() {
        brain = BrainF.createWithDefaultSize()
        source = ListCode.create("")
        brain.code.source = source
    }

    @Test
    public void gotoCorrectEndWhile() {
        source.addCommands(">+>[-]+   ")
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
        source.addCommands("++[>+++<-]>>>");
        brain.run();
        assert [ 0, 6, 0, 0, 0, 0, 0, 0, 0, 0 ] == brain.getMemory().getMemoryArray(0, 10)
    }

    @Test
    public void analyzeLoops() {
        source.addCommands("++[ > +++++[>+>+++<<-]>[>+<-]<[+-+-]> +++ << -]");
        analyze()
        IndexCounters counts = analyze.getWhileLoopCounts();
        assert counts.size() == 4
        assert counts[2] == [2]
        assert counts[11] == [5, 5]
        assert counts[23] == [5, 8]
        assert counts[30] == [0, 0]
    }

    @Test
    public void loopOnce() {
        source.addCommands("+[-]");
        analyze()
        IndexCounters counts = analyze.getWhileLoopCounts();
        assert counts.size() == 1
        assert counts[1] == [1]
    }

    @Test
    public void printAlphabet() {
        source.addCommands("++++++[>++++++++++>++++<<-]>+++++>++[-<.+>]");
        brain.run();
        assert brain.getOutput() == "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    }

    @Test
    public void fizzBuzz() {
        source.addCommands(BrainfuckRunner.classLoader.getResource('fizzbuzz.bf').text);
        analyze()
        assert analyze.getActionsForCommand(BrainFCommand.WRITE) == brain.output.length()
        assert brain.output == fizzBuzzString(100)
    }

    @Test
    public void fizzBuzzMin() {
        source.addCommands(BrainfuckRunner.classLoader.getResource('fizzbuzz-min.bf').text);
        analyze()
        assert analyze.getActionsForCommand(BrainFCommand.WRITE) == brain.output.length()
        assert brain.output == fizzBuzzString(100)
    }

    @Test
    public void printedMemory() {
        source.addCommands('>.<+++.[-.]')
        analyze()
        assert analyze.cell(0).prints.toString() == '[6, 9 * 3]' // printed by code index 6 once, code index 9 thrice
        assert analyze.cell(1).prints.toString() == '[1]'
    }

    @Test
    public void includeTest() {
        source.addCommands(BrainfuckRunner.classLoader.getResource('include-base.bf').text);
        brain.run()
        assert brain.memory.getMemoryArray(0, 5) == [0, 0, 12, 0, 0] as int[]
    }

    @Test
    public void stepContinueStrategy() {
        source.addCommands("+++[>+<-]-");
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
        source.addCommands("+++[>+<-]-");
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
        source.addCommands(">>>>+++++++++++++++[<+++++++++++++++++>-]<[->[+>>]+[<<]>]")
        analyze()
        analyze.print()
    }

    @Test
    public void readsAndWrites() {
        source.addCommands(">> +++++ [->[+>>]+[<<]>]")
        // distribute values from 5 downto 1 across the tape
        analyze()
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
        source.addCommands("+>++>+++<")
        brain.run();
        assert 9 == brain.code.commandIndex
        assert 1 == brain.memory.memoryIndex
        assert 2 == brain.memory.memory
        brain.perform(BrainFCommand.PREVIOUS)
        assert 1 == brain.memory.memory
        brain.perform(BrainFCommand.NEXT)
        brain.perform(BrainFCommand.NEXT)
        assert 3 == brain.memory.memory
    }

}

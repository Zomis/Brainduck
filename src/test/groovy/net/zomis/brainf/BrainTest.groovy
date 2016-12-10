package net.zomis.brainf

import groovy.transform.CompileStatic
import net.zomis.brainf.analyze.IndexCounters
import net.zomis.brainf.analyze.MemoryCell
import net.zomis.brainf.analyze.analyzers.CommandCountAnalysis
import net.zomis.brainf.analyze.analyzers.IOAnalysis
import net.zomis.brainf.analyze.analyzers.MemoryValues
import net.zomis.brainf.analyze.analyzers.ReadWriteAnalysis
import net.zomis.brainf.analyze.analyzers.WhileLoopAnalysis
import net.zomis.brainf.model.BrainF
import net.zomis.brainf.model.ast.BFToken
import net.zomis.brainf.model.ast.tree.ChangePointerSyntax
import net.zomis.brainf.model.ast.tree.ChangeValueSyntax
import net.zomis.brainf.model.ast.tree.LoopInstructionSyntax
import net.zomis.brainf.model.ast.tree.SyntaxTree
import net.zomis.brainf.model.classic.BrainFCommand
import net.zomis.brainf.model.BrainfuckMemory
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.input.FixedInput
import net.zomis.brainf.model.input.StringBuilderOutput
import net.zomis.brainf.model.run.StepContinueStrategy
import net.zomis.brainf.model.run.StepOutStrategy
import net.zomis.brainf.model.run.UntilEndStrategy
import org.junit.Test

import java.util.concurrent.TimeUnit

@CompileStatic
public class BrainTest extends BrainfuckTest {

    @Test(expected = BrainfuckCompilationException)
    public void failUnmatchedOpeningBracet() {
        useCode(">+>[-]+++[-->++]-->+++[ /* this is unmatched */ ---++[--<++]--");
    }

    @Test
    public void gotoCorrectEndWhile() {
        useCode(">+>[-]+   " +
            "++[-->++]-->   Find next 254 and go one step beyond it" +
            "            Loop through all 254s" +
            "+++[---         Make sure that we are not at 253 (end)" +
            "++[--<++]--                ]");

        assert (brain.step() as ChangePointerSyntax).value == 1
        assert (brain.step() as ChangeValueSyntax).value == 1
        assert (brain.step() as ChangePointerSyntax).value == 1
        assert brain.step() instanceof LoopInstructionSyntax

        // assert brain.code.currentSyntax.getTokens().get(0).info.position == 6
        assert (brain.step() as ChangeValueSyntax).value == 1
    }

    @Test(timeout = 10000L)
    public void namedLoops() {
        String commands = '''
        $ nextLoop 'before'
        +++[-]
        >+[-
            $ loop 'inside'
        ]
        $ nextLoops 'nested'
        >+[++[-]+-]
        >+[-]
        $ lastLoop 'after'
        >++++[-
            $ loop 'duplicate'
        ]
        '''
        useCode(commands)
        analyze(new WhileLoopAnalysis())
        analyze.print()
        println context.getLoopNames()
        cellTagsContains(analyze.cell(0), 'before')
        cellTagsContains(analyze.cell(1), 'inside')
        assert analyze.cell(2).resolveTags(context).entrySet().stream().filter({
            it.key.contains('nested') && it.key.contains('loop-begin')
        }).count() == 2 // expect two kinds of 'loop-begin nested' tags
        cellTagsContains(analyze.cell(3), 'after')
        cellTagsContains(analyze.cell(4), 'duplicate')
        assert analyze.cell(4).resolveTags(context).entrySet().stream().filter({
            it.key.contains('duplicate')
        }).mapToInt({it.key.count('duplicate')}).max().orElse(0) == 1 // do not expect any duplicate tag names
    }

    @Test
    public void namedCells() {
        String commands = '''
        +++
        >
        $ name 'test'
        ++[-]
        '''
        useCode(commands)
        analyze(new WhileLoopAnalysis())
        assert analyze.cell(1).resolveTags(context).get('test') == 1
    }

    @Test(timeout = 1000L)
    public void userInputTag() {
        String commands = '++++[->,<]'
        brain = new BrainfuckRunner(new BrainfuckMemory(30), BrainF.code(commands), new FixedInput('INPUT'), null)
        analyze(new IOAnalysis())
        cellTagsContains(analyze.cell(1), 'userInput')
    }

    private void cellTagsContains(MemoryCell cell, String text) {
        assert cell.toString(context).contains(text)
/*        assert cell.resolveTags(context).entrySet().stream().filter({
        }).findAny().isPresent()*/
    }

    @Test
    public void simpleLoopMultiplication() {
        useCode("++[>+++<-]>>>");
        brain.run(new UntilEndStrategy());
        assert [ 0, 6, 0, 0, 0, 0, 0, 0, 0, 0 ] as int[] ==
            brain.getMemory().getMemoryArray(0, 10)
    }

    @Test
    public void analyzeLoops() {
        useCode("++[ > +++++[>+>+++<<-]>[>+<-]<[+-+-]> +++ << -]");
        analyze(new WhileLoopAnalysis())
        IndexCounters counts = analyze.get(WhileLoopAnalysis).getWhileLoopCounts()
        assert counts.size() == 4
        assert counts[2] == [2]
        assert counts[11] == [5, 5]
        assert counts[23] == [5, 8]
        assert counts[30] == [0, 0]
    }

    @Test
    public void loopOnce() {
        useCode("+[-]");
        assert brain.code.rootTree.syntax.size() == 2
        assert (brain.code.rootTree.syntax[0] as ChangeValueSyntax).value == 1
        assert (brain.code.rootTree.syntax[1] as SyntaxTree).syntax.size() == 1
        analyze(new WhileLoopAnalysis())
        IndexCounters counts = analyze.get(WhileLoopAnalysis).getWhileLoopCounts();
        assert counts.size() == 1
        // While loop at index 1 is performed once
        assert counts[1] == [1]
    }

    @Test
    public void printAlphabet() {
        useCode("++++++[>++++++++++>++++<<-]>+++++>++[-<.+>]");
        brain.run(new UntilEndStrategy());
        assert output.toString() == "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    }

    @Test(expected = BrainfuckCompilationException)
    public void unbalanced1() {
        useCode("++[->+<] ]")
    }

    @Test
    public void fizzBuzz() {
        useCode(BrainfuckRunner.classLoader.getResource('fizzbuzz.bf').text);
        long start = System.nanoTime()
        analyzeAll()
        long stop = System.nanoTime()
        long ms = TimeUnit.MILLISECONDS.convert(stop - start, TimeUnit.NANOSECONDS)
        println "FizzBuzz analyze took $ms ms"
        assert analyze.get(CommandCountAnalysis).getActionsForCommand(BrainFCommand.WRITE.name()) == output.length()
        assert output.toString() == fizzBuzzString(100)
    }

    @Test
    public void fizzBuzzMin() {
        useCode(BrainfuckRunner.classLoader.getResource('fizzbuzz-min.bf').text);
        analyzeAll()
        assert analyze.get(CommandCountAnalysis).getActionsForCommand(BrainFCommand.WRITE.name()) == output.length()
        assert output.toString() == fizzBuzzString(100)
    }

    @Test
    public void correctTokens() {
        useCode('>.<+++.[-.]')
        def add = brain.code.rootTree.syntax[3]
        assert (add as ChangeValueSyntax).value == 3
        assert add.tokens.stream().allMatch({ (it as BFToken).command == BrainFCommand.ADD })
        assert add.tokens.size() == 3
        assert add.tokens[0].info.position == 3
        assert add.tokens[1].info.position == 4
        assert add.tokens[2].info.position == 5
        assert brain.code.rootTree.syntax[4].tokens.size() == 1
        assert brain.code.rootTree.syntax[4].tokens[0].info.position == 6
    }

    @Test
    public void printedMemory() {
        useCode('>.<+++.[-.]')
        analyze(new IOAnalysis())
        assert analyze.cell(0).data(IOAnalysis.CellIO).prints.toString() == '[6, 9 * 3]' // printed by code index 6 once, code index 9 thrice
        assert analyze.cell(1).data(IOAnalysis.CellIO).prints.toString() == '[1]'
    }

    @Test
    public void includeTest() {
        useCode(BrainfuckRunner.classLoader.getResource('include-base.bf').text);
        brain.run(new UntilEndStrategy())
        assert brain.memory.getMemoryArray(0, 5) == [0, 0, 12, 0, 0] as int[]
    }

    @Test
    public void stepContinueStrategy() {
        useCode("+++[>+<-]-");
        assert brain.step()
        brain.run(new StepContinueStrategy())
        assert brain.step()
        assert brain.memory.getMemory(0) == 3

        brain.run(new StepContinueStrategy())
        assert (brain.code.currentSyntax as ChangePointerSyntax).value == 1
        assert brain.memory.getMemory(0) == 2
        assert brain.memory.getMemory(1) == 1

        brain.run(new StepContinueStrategy())
        assert brain.memory.getMemory(0) == 1
        assert (brain.code.currentSyntax as ChangePointerSyntax).value == 1

        brain.run(new StepContinueStrategy())
        assert brain.memory.getMemory(0) == 0
        assert (brain.code.currentSyntax as ChangeValueSyntax).value == -1

    }

    @Test
    public void stepOutStrategy() {
        useCode("+++[>+<-]-");
        assert brain.step() instanceof ChangeValueSyntax
        assert brain.step() instanceof ChangeValueSyntax
        assert brain.step() instanceof ChangeValueSyntax
        assert brain.run(new StepOutStrategy()) == 0
        assert brain.step() instanceof SyntaxTree
        assert brain.run(new StepOutStrategy())
        assert brain.memory.getMemory(1) == 3
        assert (brain.code.currentSyntax as ChangeValueSyntax).value == -1
        assert brain.isOnRootTree()
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
        useCode(">>>>+++++++++++++++[<+++++++++++++++++>-]<[->[+>>]+[<<]>]")
        analyzeAll()
        analyze.print()
    }

    @Test
    public void readsAndWrites() {
        useCode(">> +++++ [->[+>>]+[<<]>]")
        // distribute values from 5 downto 1 across the tape
        analyze(new MemoryValues(), new ReadWriteAnalysis())
        analyze.print()
        ReadWriteAnalysis readWrite = analyze.get(ReadWriteAnalysis)
        assert readWrite.maxMemory == 0x0B
        assert readWrite.cellsUsed == 7 // 5 cells gets values + 2 for loop check
        assert readWrite.maxMemory + 1 - readWrite.cellsUsed == 5 // one at start + 4 between the values
        assert analyze.arrayLong(ReadWriteAnalysis.ReadWriteData, {it.readCount}, 0, 12)  ==
            [0, 5, 6, 10, 0, 8, 0, 6, 0, 4, 0, 2] as int[]
        assert analyze.arrayLong(ReadWriteAnalysis.ReadWriteData, {it.writeCount}, 0, 12) ==
            [0, 0, 10, 5, 0, 4, 0, 3, 0, 2, 0, 1] as int[]
    }

    @Test
    public void bfGroovy() {
        useCode('''+++>
$ bf '+' * 3
<---
''')
        brain.run(new UntilEndStrategy())
        assert brain.memory.getMemory(0) == 0
        assert brain.memory.getMemory(1) == 3
        assert brain.memory.getMemory(2) == 0
    }

    @Test
    public void input() {
        def str = new StringBuilder()
        BrainfuckRunner brain = new BrainfuckRunner(new BrainfuckMemory(), BrainF.code("+++,."),
          new FixedInput("a"), new StringBuilderOutput(str));
        brain.run(new UntilEndStrategy());
        assert "a" == str.toString()
    }

    @Test
    public void simpleCommands() {
        useCode("+>++>+++<")
        brain.run(new UntilEndStrategy());
        assert brain.code.commandCount == brain.code.commandIndex
        assert 1 == brain.memory.memoryIndex
        assert 2 == brain.memory.value
        brain.perform(new ChangePointerSyntax(-1))
        assert 1 == brain.memory.value
        brain.perform(new ChangePointerSyntax(1))
        brain.perform(new ChangePointerSyntax(1))
        assert 3 == brain.memory.value
    }

}

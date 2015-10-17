package net.zomis.brainf.analyze

import groovy.transform.PackageScope
import net.zomis.brainf.analyze.analyzers.ReadWriteAnalysis
import net.zomis.brainf.model.classic.BrainFCommand
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckListener
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.groovy.GroovyBFContext

import java.util.function.ToIntFunction
import java.util.function.ToLongFunction

class Brainalyze implements BrainfuckListener {

    private final int[] times
    private final int[] actionsPerCommand
    private final int[] codeCommands
    private final MemoryCell[] cells
    private final IndexCounters whileLoopCounts = new IndexCounters()
    private final List<Map> problematicCommands = []
    private final GroovyBFContext groovy
    @PackageScope final Map<Class<?>, Object> analysis = [:]
    private boolean memoryIndexBelowZero
    private int minValue
    private int maxValue
    private int maxMemory

    public <T extends BrainfuckAnalyzer> T get(Class<T> clazz) {
        return (T) analysis.get(clazz)
    }

    @PackageScope Brainalyze(BrainfuckRunner runner, GroovyBFContext groovy) {
        this.times = new int[runner.code.commandCount];
        this.actionsPerCommand = new int[BrainFCommand.values().length];
        this.codeCommands = new int[BrainFCommand.values().length];
        int size = runner.memory.memorySize
        this.cells = new MemoryCell[size]
        for (int i = 0; i < size; i++) {
            this.cells[i] = new MemoryCell(i)
        }
        this.groovy = groovy
    }

    MemoryCell cell(int index) {
        cells[index]
    }

    int[] getTimes() {
        return Arrays.copyOf(times, times.length)
    }

    int[] getActionsPerCommand() {
        return Arrays.copyOf(actionsPerCommand, actionsPerCommand.length)
    }

    @Deprecated
    static Brainalyze analyze(BrainfuckRunner brain, GroovyBFContext groovyContext) {
        Brainalyze analyze = new Brainalyze(brain, groovyContext)
        brain.setListener(analyze)
        brain.run()

        for (int i = 0; i < brain.memory.memorySize; i++) {
            analyze.cells[i].value = brain.memory.getMemory(i)
        }

        int commandCount = brain.code.commandCount
        for (int i = 0; i < commandCount; i++) {
            BrainfuckCommand command = brain.code.getCommandAt(i)
            if (command instanceof BrainFCommand) {
                BrainFCommand cmd = command as BrainFCommand
                analyze.codeCommands[cmd.ordinal()]++
            }
            if (command instanceof GroovyBFContext.SpecialCommand) {
                GroovyBFContext.SpecialCommand cmd = command as GroovyBFContext.SpecialCommand
                for (int codeIndex = 0; codeIndex < cmd.code.length(); codeIndex++) {
                    char ch = cmd.code.charAt(codeIndex)
                    BrainFCommand bfCommand = BrainFCommand.getCommand(ch)
                    if (bfCommand != BrainFCommand.NONE) {
                        Map<String, Object> map = [index: i, codeIndex: codeIndex, command: bfCommand]
                        analyze.problematicCommands << map
                        break;
                    }
                }
            }
        }

        for (int i = analyze.cells.length - 1; i >= 0; i--) {
            if (analyze.cells[i].used) {
                analyze.maxMemory = i
                break
            }
        }
        analyze
    }

    void print() {
        if (get(ReadWriteAnalysis)) {
            maxMemory = get(ReadWriteAnalysis).maxMemory
        }

        println 'Brainfuck Analyze'
        println '-----------------'
        println 'Actions per command'
        printCommands(actionsPerCommand)
        println()
        println 'Code instructions per command'
        printCommands(codeCommands)
        println()
        println 'While loops analysis'
        whileLoopCounts.sorted().forEach({entry ->
            println "$entry.key $entry.value"
        })
        println()
        if (this.memoryIndexBelowZero) {
            println 'WARNING: Memory index goes below zero'
        }
        println()
        println 'Tape summary'
        for (int i = 0; i <= maxMemory; i++) {
            MemoryCell cell = cells[i]
            println cell.toString(groovy)
        }
        println()
        this.analysis.values().forEach({it.print()})
        println()
        for (Map problem : problematicCommands) {
            println "Problematic command: $problem"
        }
    }

    static void printCommands(int[] ints) {
        int sum = 0
        BrainFCommand.values().each {
            int count = ints[it.ordinal()]
            if (count > 0) {
                println "$it: $count"
            }
            if (it != BrainFCommand.NONE) {
                sum += count
            }
        }
        println "Total: $sum"
    }

    @Override
    void beforePerform(BrainfuckRunner runner, BrainfuckCommand cmd) {
        if (!(cmd instanceof BrainFCommand)) {
            return
        }
        BrainFCommand command = (BrainFCommand) cmd
        int codeIndex = runner.code.commandIndex
        this.times[codeIndex]++
        actionsPerCommand[command.ordinal()]++
        MemoryCell cell = cells[runner.memory.memoryIndex]

        if (command == BrainFCommand.PREVIOUS && runner.memory.memoryIndex == 0) {
            this.memoryIndexBelowZero = true
        }

        if (command == BrainFCommand.READ) {
            cell.userInputs.add(codeIndex)
        }

        switch (command) {
            case BrainFCommand.ADD:
            case BrainFCommand.SUBTRACT:
            case BrainFCommand.READ:
            case BrainFCommand.WHILE:
            case BrainFCommand.END_WHILE:
            case BrainFCommand.WRITE:
                cell.used = true
                break
        }

        if (command == BrainFCommand.WRITE) {
            cell.prints.add(codeIndex)
        }

        if (command == BrainFCommand.WHILE) {
            IndexCounter counter = whileLoopCounts.getOrCreate(runner.code.commandIndex)
            int current = runner.memory.value
            if (current == 0) {
                counter.add(0)
            } else {
                whileLoopCounts.begin(counter)
                cell.whileLoopStart.add(codeIndex)
            }
        }

        if (command == BrainFCommand.END_WHILE) {
            whileLoopCounts.recent().increase()
            int startIndex = whileLoopCounts.recent().forIndex
            int current = runner.memory.value
            if (current == 0) {
                whileLoopCounts.finishLast()
                cell.whileLoopEnd.add(startIndex)
            } else {
                cell.whileLoopContinue.add(startIndex)
            }
        }
    }

    @Override
    void afterPerform(BrainfuckRunner runner, BrainfuckCommand command) {
        if (runner.memory.value > this.maxValue) {
            this.maxValue = runner.memory.value
        }
        if (runner.memory.value < this.minValue) {
            this.minValue = runner.memory.value
        }
    }

    IndexCounters getWhileLoopCounts() {
        return this.whileLoopCounts
    }

    int getActionsForCommand(BrainFCommand command) {
        this.actionsPerCommand[command.ordinal()]
    }

    int[] array(ToIntFunction<MemoryCell> function, int fromIndex, int toIndex) {
        int[] result = new int[toIndex - fromIndex]
        for (int i = 0; i < result.length; i++) {
            MemoryCell cell = cells[fromIndex + i]
            result[i] = function.applyAsInt(cell)
        }
        result
    }

    long[] arrayLong(ToLongFunction<MemoryCell> function, int fromIndex, int toIndex) {
        long[] result = new long[toIndex - fromIndex]
        for (int i = 0; i < result.length; i++) {
            MemoryCell cell = cells[fromIndex + i]
            result[i] = function.applyAsLong(cell)
        }
        result
    }

    public <T> long[] arrayLong(Class<T> clazz, ToLongFunction<T> function, int fromIndex, int toIndex) {
        long[] result = new long[toIndex - fromIndex]
        for (int i = 0; i < result.length; i++) {
            MemoryCell cell = cells[fromIndex + i]
            T obj = cell.data(clazz)
            result[i] = obj ? function.applyAsLong(obj) : 0
        }
        result
    }

    boolean isMemoryIndexBelowZero() {
        this.memoryIndexBelowZero
    }

    int getMinValue() {
        this.minValue
    }

    int getMaxValue() {
        this.maxValue
    }

}

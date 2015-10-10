package net.zomis.brainf.analyze

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
    private final GroovyBFContext groovy
    private boolean memoryIndexBelowZero
    private int minValue
    private int maxValue

    private Brainalyze(BrainfuckRunner runner, GroovyBFContext groovy) {
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

    int[] getCodeCommands() {
        return Arrays.copyOf(codeCommands, codeCommands.length)
    }

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
        }
        analyze
    }

    void print() {
        println 'Brainfuck Analyze'
        println '-----------------'
        println 'Actions per command'
        printCommands(actionsPerCommand)
        println()
        println 'Code instructions per command'
        printCommands(codeCommands)
        println()
        println 'Number of times each command has been performed'
        println times
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
        int totalUsed = 0
        int maxMemory = 0
        for (int i = cells.length - 1; i >= 0; i--) {
            if (cells[i].readCount > 0 || cells[i].writeCount > 0) {
                maxMemory = i
                break
            }
        }
        for (int i = 0; i <= maxMemory; i++) {
            MemoryCell cell = cells[i]
            println cell.toString(groovy)
            if (cell.readCount > 0 || cell.writeCount > 0) {
                totalUsed++
            }
        }
        println "Total memory used = $totalUsed"
        println()
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
                cell.writeCount++
                break
            case BrainFCommand.WHILE:
            case BrainFCommand.END_WHILE:
            case BrainFCommand.WRITE:
                cell.readCount++
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

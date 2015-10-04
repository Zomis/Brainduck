package net.zomis.brainf.analyze

import net.zomis.brainf.model.classic.BrainFCommand
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckListener
import net.zomis.brainf.model.BrainfuckRunner

import java.util.concurrent.atomic.AtomicInteger
import java.util.function.ToIntFunction
import java.util.function.ToLongFunction

class Brainalyze implements BrainfuckListener {

    private final int[] times
    private final int[] actionsPerCommand
    private final int[] codeCommands
    private final MemoryCell[] cells
    private final Map<Integer, List<Integer>> whileLoopCounts = new HashMap<>()

    private Brainalyze(BrainfuckRunner runner) {
        this.times = new int[runner.code.commandCount];
        this.actionsPerCommand = new int[BrainFCommand.values().length];
        this.codeCommands = new int[BrainFCommand.values().length];
        int size = runner.memory.memorySize
        this.cells = new MemoryCell[size]
        for (int i = 0; i < size; i++) {
            this.cells[i] = new MemoryCell(i)
        }
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

    static Brainalyze analyze(BrainfuckRunner brain) {
        Brainalyze analyze = new Brainalyze(brain)
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
        whileLoopCounts.entrySet().stream().sorted(Comparator.comparingInt({it.key})).forEach({entry ->
            print "$entry.key "
            printCompactList(entry.value)
            println()
        })
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
            int value = cell.value
            String hexAddress = String.format("%04X", i)
            String decAddress = String.format("%06d", i)

            boolean specialChar = value >= 0 && value <= 13
            char chrValue = specialChar ? 32 : value;
            String decValue = String.format("%6d", value);

            String reads = String.format("%6d", cell.readCount);
            String writes = String.format("%6d", cell.writeCount);
            println "Hex $hexAddress\tDec $decAddress\tValue $decValue '$chrValue' \tReads: $reads\tWrites: $writes"
            if (cell.readCount > 0 || cell.writeCount > 0) {
                totalUsed++
            }
        }
        println "Total memory used = $totalUsed"
        println()
    }

    static void printCompactList(List<?> values) {
        int count = 0
        Object value = null
        print '['
        boolean shouldPrintComma = false
        for (Object i : values) {
            if (Objects.equals(i, value)) {
                count++
            } else {
                if (shouldPrintComma) {
                    print ', '
                }
                if (count > 0) {
                    shouldPrintComma = true
                    print countString(count, value)
                }
                count = 1
                value = i
            }
        }
        if (shouldPrintComma) {
            print ', '
        }
        print countString(count, value)
        print ']'
    }

    static String countString(int count, Object value) {
        count >= 2 ? "$value * $count" : "$value"
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

    private final Stack<AtomicInteger> enteredLoops = new Stack<>()

    @Override
    void beforePerform(BrainfuckRunner runner, BrainfuckCommand cmd) {
        if (!(cmd instanceof BrainFCommand)) {
            return
        }
        BrainFCommand command = (BrainFCommand) cmd
        this.times[runner.code.commandIndex]++
        actionsPerCommand[command.ordinal()]++
        MemoryCell cell = cells[runner.memory.memoryIndex]

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

        if (command == BrainFCommand.WHILE) {
            whileLoopCounts.putIfAbsent(runner.code.commandIndex, new ArrayList<Integer>())
            int current = runner.memory.getMemory()
            if (current == 0) {
                whileLoopCounts.get(runner.code.commandIndex).add(0)
            } else {
                enteredLoops.add(new AtomicInteger())
            }
        }

        if (command == BrainFCommand.END_WHILE) {
            enteredLoops.peek().incrementAndGet()
            int current = runner.memory.getMemory()
            if (current == 0) {
                int startPos = runner.code.findMatching(BrainFCommand.WHILE, BrainFCommand.END_WHILE, -1)
                int count = enteredLoops.pop().get()
                whileLoopCounts.get(startPos).add(count)
            }
        }
    }

    @Override
    void afterPerform(BrainfuckRunner runner, BrainfuckCommand command) {

    }

    Map<Integer, List<Integer>> getWhileLoopCounts() {
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

}

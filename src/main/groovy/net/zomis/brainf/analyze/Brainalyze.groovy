package net.zomis.brainf.analyze

import net.zomis.brainf.BrainFCommand
import net.zomis.brainf.model.BrainfuckListener
import net.zomis.brainf.model.BrainfuckRunner

import java.util.concurrent.atomic.AtomicInteger

class Brainalyze implements BrainfuckListener {

    private final int[] times
    private final int[] actionsPerCommand
    private final int[] codeCommands
    private final Map<Integer, List<Integer>> whileLoopCounts = new HashMap<>()

    Brainalyze(BrainfuckRunner runner) {
        this.times = new int[runner.code.commandCount];
        this.actionsPerCommand = new int[BrainFCommand.values().length];
        this.codeCommands = new int[BrainFCommand.values().length];
    }

    static Brainalyze analyze(BrainfuckRunner brain) {
        Brainalyze analyze = new Brainalyze(brain)
        brain.setListener(analyze)
        brain.run()

        int commandCount = brain.code.commandCount
        for (int i = 0; i < commandCount; i++) {
            BrainFCommand command = brain.code.getCommandAt(i)
            analyze.codeCommands[command.ordinal()]++
        }

        for (int i = 0; i < analyze.times.length; i++) {
            if (analyze.times[i] <= 0) {
                BrainFCommand command = brain.code.getCommandAt(i)
                println "Dead code at $i: $command"
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
        println times
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

    private final Stack<AtomicInteger> enteredLoops = new Stack<>()

    @Override
    void beforePerform(BrainfuckRunner runner, BrainFCommand command) {
        this.times[runner.code.commandIndex]++
        actionsPerCommand[command.ordinal()]++

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
    void afterPerform(BrainfuckRunner runner, BrainFCommand command) {

    }

    Map<Integer, List<Integer>> getWhileLoopCounts() {
        return this.whileLoopCounts
    }

}

package net.zomis.brainf.analyze

import net.zomis.brainf.BrainFCommand
import net.zomis.brainf.model.BrainfuckListener
import net.zomis.brainf.model.BrainfuckRunner

class Brainalyze implements BrainfuckListener {

    private final int[] times
    private final int[] actionsPerCommand
    private final int[] codeCommands

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
        println actionsPerCommand
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
    void beforePerform(BrainfuckRunner runner, BrainFCommand command) {
        this.times[runner.code.commandIndex]++
        actionsPerCommand[command.ordinal()]++
    }

    @Override
    void afterPerform(BrainfuckRunner runner, BrainFCommand command) {

    }
}

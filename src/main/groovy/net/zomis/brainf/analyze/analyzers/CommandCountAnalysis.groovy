package net.zomis.brainf.analyze.analyzers

import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.analyze.MemoryCell
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.classic.BrainFCommand

class CommandCountAnalysis implements BrainfuckAnalyzer {

    private int[] times
    private int[] actionsPerCommand
    private int[] codeCommands

    @Override
    void beforeStart(BrainfuckRunner runner) {
        this.times = new int[runner.code.commandCount];
        this.actionsPerCommand = new int[BrainFCommand.values().length];
        this.codeCommands = new int[BrainFCommand.values().length];
    }

    @Override
    void print() {
        println 'Actions per command'
        printCommands(actionsPerCommand)
        println()
        println 'Code instructions per command'
        printCommands(codeCommands)
        println()
    }

    @Override
    void beforePerform(MemoryCell cell, BrainfuckRunner runner, BrainfuckCommand cmd) {
        int codeIndex = runner.code.commandIndex
        this.times[codeIndex]++
        if (!(cmd instanceof BrainFCommand)) {
            return
        }
        def command = cmd as BrainFCommand
        actionsPerCommand[command.ordinal()]++
    }

    @Override
    void after(Brainalyze analyze, BrainfuckRunner runner) {
        int commandCount = runner.code.commandCount
        for (int i = 0; i < commandCount; i++) {
            BrainfuckCommand command = runner.code.getCommandAt(i)
            if (command instanceof BrainFCommand) {
                BrainFCommand cmd = command as BrainFCommand
                codeCommands[cmd.ordinal()]++
            }
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

    int getActionsForCommand(BrainFCommand command) {
        this.@actionsPerCommand[command.ordinal()]
    }

    int[] getTimes() {
        return Arrays.copyOf(this.@times, this.@times.length)
    }

    int[] getActionsPerCommand() {
        return Arrays.copyOf(this.@actionsPerCommand, this.@actionsPerCommand.length)
    }

}

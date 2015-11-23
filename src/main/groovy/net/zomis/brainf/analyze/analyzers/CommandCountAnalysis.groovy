package net.zomis.brainf.analyze.analyzers

import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.analyze.MemoryCell
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.classic.BrainFCommand

import java.util.function.BiFunction

class CommandCountAnalysis implements BrainfuckAnalyzer {

    private int[] times
    private final Map<BrainfuckCommand, Integer> actionsPerCommand = [:]
    private final Map<BrainfuckCommand, Integer> codeCommands = [:]

    private static final BiFunction<Integer, Integer, Integer> MERGE_FUNCTION = {a, b -> a + b}

    @Override
    void beforeStart(BrainfuckRunner runner) {
        this.times = new int[runner.code.commandCount];
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
    void beforePerform(MemoryCell cell, BrainfuckRunner runner, BrainfuckCommand command) {
        int codeIndex = runner.code.commandIndex
        this.times[codeIndex]++
        actionsPerCommand.merge(command, 1, MERGE_FUNCTION)
    }

    @Override
    void after(Brainalyze analyze, BrainfuckRunner runner) {
        int commandCount = runner.code.commandCount
        for (int i = 0; i < commandCount; i++) {
            BrainfuckCommand command = runner.code.getCommandAt(i)
            codeCommands.merge(command, 1, MERGE_FUNCTION)
        }
    }

    static void printCommands(Map<BrainfuckCommand, Integer> ints) {
        int sum = 0
        ints.entrySet().stream().forEach({
            BrainfuckCommand command = it.key
            Integer count = it.value
            if (count > 0) {
                println "$command: $count"
            }
            if (it != BrainFCommand.NONE) {
                sum += count
            }
        })
        println "Total: $sum"
    }

    int getActionsForCommand(BrainFCommand command) {
        this.@actionsPerCommand[command]
    }

    int[] getTimes() {
        return Arrays.copyOf(this.@times, this.@times.length)
    }

    Map<BrainfuckCommand, Integer> getActionsPerCommand() {
        return new HashMap<>(this.@actionsPerCommand)
    }

    Map<BrainfuckCommand, Integer> getCodeCommands() {
        return new HashMap<>(this.@codeCommands)
    }

}

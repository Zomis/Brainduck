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

    private final MemoryCell[] cells
    private final GroovyBFContext groovy
    @PackageScope final Map<Class<?>, Object> analysis = [:]
    private int maxMemory

    public <T extends BrainfuckAnalyzer> T get(Class<T> clazz) {
        return (T) analysis.get(clazz)
    }

    @PackageScope Brainalyze(BrainfuckRunner runner, GroovyBFContext groovy) {
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

    @Deprecated
    static Brainalyze analyze(BrainfuckRunner brain, GroovyBFContext groovyContext) {
        Brainalyze analyze = new Brainalyze(brain, groovyContext)
        brain.setListener(analyze)
        brain.run()

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
        println 'Tape summary'
        for (int i = 0; i <= maxMemory; i++) {
            MemoryCell cell = cells[i]
            println cell.toString(groovy)
        }
        println()
        this.analysis.values().forEach({it.print()})
        println()
    }

    @Override
    void beforePerform(BrainfuckRunner runner, BrainfuckCommand cmd) {
        if (!(cmd instanceof BrainFCommand)) {
            return
        }
        BrainFCommand command = (BrainFCommand) cmd
        MemoryCell cell = cells[runner.memory.memoryIndex]

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

    }

    @Override
    void afterPerform(BrainfuckRunner runner, BrainfuckCommand command) {
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

}

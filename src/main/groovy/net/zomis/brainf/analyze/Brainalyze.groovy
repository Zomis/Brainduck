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

class Brainalyze {

    private final MemoryCell[] cells
    private final GroovyBFContext groovy
    @PackageScope final Map<Class<?>, Object> analysis = [:]
    private int maxMemory
    private final List<InspectionResult> inspectionResults = []
    @PackageScope long timeSpent

    public <T extends BrainfuckAnalyzer> T get(Class<T> clazz) {
        return (T) analysis.get(clazz)
    }

    Brainalyze addInspectionResult(InspectionResult result) {
        this.@inspectionResults.add(result)
        this
    }

    List<InspectionResult> getInspectionResults() {
        new ArrayList<InspectionResult>(this.@inspectionResults)
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
        println("Analyze completed in ${timeSpent / 1000000.0} ms")
        println()
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

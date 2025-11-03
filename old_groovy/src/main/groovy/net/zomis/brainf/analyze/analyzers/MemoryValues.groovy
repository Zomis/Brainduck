package net.zomis.brainf.analyze.analyzers

import net.zomis.brainf.analyze.Brainalyze
import net.zomis.brainf.analyze.BrainfuckAnalyzer
import net.zomis.brainf.analyze.MemoryCell
import net.zomis.brainf.model.BrainfuckCommand
import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ast.tree.Syntax

class MemoryValues implements BrainfuckAnalyzer {

    public static class MemoryCellValue {
        int value

        String toString() {
            int value = this.value
            boolean specialChar = value >= 0 && value <= 13
            char chrValue = specialChar ? 32 : value;
            String decValue = String.format("%6d", value);
            "Value $decValue '$chrValue'"
        }

    }

    private int minValue
    private int maxValue

    @Override
    void afterPerform(MemoryCell cell, BrainfuckRunner runner, Syntax command) {
        if (runner.memory.value > this.maxValue) {
            this.maxValue = runner.memory.value
        }
        if (runner.memory.value < this.minValue) {
            this.minValue = runner.memory.value
        }
    }

    @Override
    Object createMemoryData() {
        return new MemoryCellValue()
    }

    @Override
    void after(Brainalyze analyze, BrainfuckRunner runner) {
        for (int i = 0; i < runner.memory.memorySize; i++) {
            analyze.cell(i).data(this, MemoryCellValue).value = runner.memory.getMemory(i)
        }
    }

    int getMinValue() {
        this.@minValue
    }

    int getMaxValue() {
        this.@maxValue
    }

}

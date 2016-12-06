package net.zomis.brainf.analyze;

import net.zomis.brainf.model.BrainfuckCommand;
import groovy.transform.CompileStatic;
import net.zomis.brainf.model.BrainfuckRunner;

@CompileStatic
public interface BrainfuckAnalyzer {

    default Object createMemoryData() { return null; }
    default void beforeStart(BrainfuckRunner runner) { }
    default void after(Brainalyze analyze, BrainfuckRunner runner) { }

    default void print() { }

    default void beforePerform(MemoryCell cell, BrainfuckRunner runner, BrainfuckCommand command) { }

    default void afterPerform(MemoryCell cell, BrainfuckRunner runner, BrainfuckCommand command) { }

}

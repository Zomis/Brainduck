package net.zomis.brainf.analyze;

import groovy.transform.CompileStatic;
import net.zomis.brainf.model.BrainfuckRunner;
import net.zomis.brainf.model.ast.tree.Syntax;

@CompileStatic
public interface BrainfuckAnalyzer {

    default Object createMemoryData() { return null; }
    default void beforeStart(BrainfuckRunner runner) { }
    default void after(Brainalyze analyze, BrainfuckRunner runner) { }

    default void print() { }

    default void beforePerform(MemoryCell cell, BrainfuckRunner runner, Syntax command) { }
    default void afterPerform(MemoryCell cell, BrainfuckRunner runner, Syntax command) { }

    default void beforeWhile(MemoryCell cell, BrainfuckRunner runner) {}
    default void afterWhile(MemoryCell cell, BrainfuckRunner runner) {}
    default void beforeEndWhile(MemoryCell cell, BrainfuckRunner runner) {}
    default void afterEndWhile(MemoryCell cell, BrainfuckRunner runner) {}

}

package net.zomis.brainf.model;

import net.zomis.brainf.model.ast.tree.Syntax;

public interface BrainfuckListener {
    default void beforePerform(BrainfuckRunner runner, Syntax command) {}
    default void afterPerform(BrainfuckRunner runner, Syntax command) {}

    default void beforeWhile(BrainfuckRunner runner) {}
    default void afterWhile(BrainfuckRunner runner) {}
    default void beforeEndWhile(BrainfuckRunner runner) {}
    default void afterEndWhile(BrainfuckRunner runner) {}
}

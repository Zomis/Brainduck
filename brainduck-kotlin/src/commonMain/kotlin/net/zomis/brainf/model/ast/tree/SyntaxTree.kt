package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner

open class SyntaxTree : Syntax(), Iterable<Syntax> {

    val syntax = mutableListOf<Syntax>()

    override fun perform(runner: BrainfuckRunner) {
        throw IllegalStateException("Cannot perform a full syntax tree at once");
    }

    override fun iterator(): ListIterator<Syntax> {
        return syntax.listIterator()
    }

    fun iteratorAt(index: Int): ListIterator<Syntax> {
        return syntax.listIterator(index)
    }

    override fun toString(): String {
        return "SyntaxTree{" +
                "syntax=" + syntax +
                '}';
    }

}

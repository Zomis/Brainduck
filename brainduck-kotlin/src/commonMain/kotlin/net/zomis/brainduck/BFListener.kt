package net.zomis.brainduck

import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ast.tree.Syntax

interface BFListener {

    fun beforePerform(runner: BrainfuckRunner, command: Syntax) {}
    fun afterPerform(runner: BrainfuckRunner, command: Syntax) {}

    fun beforeWhile(runner: BrainfuckRunner) {}
    fun afterWhile(runner: BrainfuckRunner) {}
    fun beforeEndWhile(runner: BrainfuckRunner) {}
    fun afterEndWhile(runner: BrainfuckRunner) {}


}
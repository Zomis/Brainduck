package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ast.Token

abstract class Syntax {

    val _tokens = mutableListOf<Token?>()
    val tokens = _tokens.toList()

    abstract fun perform(runner: BrainfuckRunner)

}

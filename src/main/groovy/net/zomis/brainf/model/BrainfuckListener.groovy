package net.zomis.brainf.model

import net.zomis.brainf.model.ast.tree.Syntax

interface BrainfuckListener {

    void beforePerform(BrainfuckRunner runner, Syntax command)

    void afterPerform(BrainfuckRunner runner, Syntax command)

}

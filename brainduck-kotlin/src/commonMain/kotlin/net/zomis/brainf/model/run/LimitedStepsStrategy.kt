package net.zomis.brainf.model.run

import net.zomis.brainf.model.BrainfuckRunner
import net.zomis.brainf.model.ast.tree.CommentSyntax
import net.zomis.brainf.model.ast.tree.Syntax

class LimitedStepsStrategy(private val count: Int = 1) : RunStrategy {

    private var remaining: Int = count

    override fun start(runner: BrainfuckRunner): Boolean {
        remaining = count
        return true
    }

    override fun next(runner: BrainfuckRunner): Boolean {
        if (remaining > 0) {
            val comm: Syntax? = runner.step()
            if (!isSkipSyntax(comm)) {
                remaining--
                println("Step: $comm")
            }
            return true
        } else {
            val comm: Syntax? = runner.code.currentSyntax
            if (isSkipSyntax(comm)) {
                runner.step()
            }
            return isSkipSyntax(comm)
        }
    }

    fun isSkipSyntax(syntax: Syntax?): Boolean = syntax is CommentSyntax

}

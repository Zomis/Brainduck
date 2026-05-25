package net.zomis.brainf.model

import net.zomis.brainduck.BFListener
import net.zomis.brainduck.Input
import net.zomis.brainduck.RunStrategy
import net.zomis.brainf.model.ast.tree.SteppableSyntax
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.ast.tree.SyntaxTree

class BrainfuckRunner(
    val memory: BrainfuckMemory,
    val code: BrainfuckCode,
    val input: Input,
    val output: BrainfuckOutput,
) {
    private var listener: BFListener = object : BFListener {}

    @Deprecated("")
    fun getOutput(): String = output.toString()

    fun getOutputBuilder(): BrainfuckOutput {
        return output
    }

    @Deprecated("")
    fun run() {
        while (code.hasMoreCommands()) {
            step();
        }
    }

    fun step(): Syntax? {
        /*
        * check current node
        * run current node
        * if current node is syntaxtree, enter syntaxtree (if condition is ok)
        * if current node is last one in syntax tree, then go to parent syntaxtree again,
        * and on next run determine whether to enter it or not (to simulate end loop ']' )
        */
        println("Perform step with memory " + memory.values(0, 5).contentToString() + " and index $memory.memoryIndex")
        if (code.isFinished()) {
            return null
        }

        val currentSyntax = code.currentSyntax
        if (code.currentSyntax is SteppableSyntax) {
            println("Step: " + code.currentSyntax)
            val steppableSyntax: SteppableSyntax = code.currentSyntax as SteppableSyntax
            if (code.positionInSyntax == 0) {
                listener.beforePerform(this, code.currentSyntax!!)
            }
            steppableSyntax.performTimes(this, 1)
            code.positionInSyntax++
            if (code.positionInSyntax == steppableSyntax.times) {
                println("Syntax done: " + code.currentSyntax)
                listener.afterPerform(this, code.currentSyntax!!)
                code.positionInSyntax = 0;
                gotoNextSyntax()
            }
            return currentSyntax;
        } else {
            val syntax = code.currentSyntax
            println("Perform: " + syntax)
            perform(syntax)
        }

        gotoNextSyntax()
        return currentSyntax;
    }

    fun runSyntax(): Syntax? {
        val syntax = code.currentSyntax
        if (code.positionInSyntax != 0) {
            // Perform the rest of the active syntax
            val steppableSyntax: SteppableSyntax = syntax as SteppableSyntax
            steppableSyntax.performTimes(this, steppableSyntax.times - code.positionInSyntax)
            code.positionInSyntax = 0
            listener.afterPerform(this, syntax)
            gotoNextSyntax()
        } else {
            perform(syntax)
            gotoNextSyntax()
        }
        return syntax
    }

    private fun gotoNextSyntax() {
        val currentSyntax = code.currentSyntax
        println("Goto next after " + currentSyntax)
        if (currentSyntax is SyntaxTree) {
            enterWhile(currentSyntax)
        } else if (currentSyntax == null || code.getSyntaxIndex() == code.currentTree.size - 1) {
            endWhile()
        } else {
            println("${code.getSyntaxIndex()} != ${code.currentTree.size - 1}")
            // go to next
            code.currentTree.stepForward()
            println("Step forward.")
        }
    }

    private fun enterWhile(currentSyntax: SyntaxTree) {
        listener.beforeWhile(this)
        listener.beforePerform(this, currentSyntax)
        // enter syntax tree if condition is ok, otherwise skip to next syntax
        if (memory.value != 0) {
            println("Entering syntax tree $currentSyntax")
            code.enteredTrees.push(SyntaxTreePosition(currentSyntax))
        } else {
            println("Loop zero at pos $memory.memoryIndex, skipping entering.")
            code.currentTree.stepForward()
        }
        listener.afterWhile(this)
    }

    private fun endWhile() {
        var anotherEndWhile = false
        println("Last step in syntax. Depth ${code.enteredTrees.size}")
        // go to parent syntax tree or reset syntax tree
        if (!isOnRootTree()) {
            listener.beforeEndWhile(this)
        }
        if (memory.value != 0 && !isOnRootTree()) {
            code.enteredTrees.peek().restart()
        } else {
            val previousTree = code.enteredTrees.pop()
            listener.afterPerform(this, previousTree.tree)
            if (!code.enteredTrees.isEmpty()) {
                code.currentTree.stepForward() // Go past the loop.
                if (code.currentSyntax == null) {
                    anotherEndWhile = true;
                }
            }
        }
        if (!isOnRootTree()) {
            listener.afterEndWhile(this)
        }
        if (anotherEndWhile) {
            endWhile()
        }
    }

    fun isOnRootTree(): Boolean = code.enteredTrees.size == 1

    fun reset() {
        memory.reset()
        code.resetIndex()
    }

    fun perform(syntax: Syntax?) {
        listener.beforePerform(this, syntax!!)
        if (syntax != null) {
            syntax.perform(this)
        }
        listener.afterPerform(this, syntax!!)
    }

    fun setListener(listener: BFListener) {
        this.listener = listener
    }

    fun run(strategy: net.zomis.brainf.model.run.RunStrategy): Int {
        var count = 0
        var repeat = strategy.start(this)
        while (repeat) {
            count++
            repeat = strategy.next(this)
        }
        return count
    }

    fun appendOutput(value: Char) {
        output.write(value);
    }

}

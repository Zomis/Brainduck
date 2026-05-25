package net.zomis.brainf.model

import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.ast.tree.SyntaxTree

class Stack<T> {
    private val values = mutableListOf<T>()
    val size get() = values.size

    fun peek(): T {
        return values.last()
    }

    fun isEmpty(): Boolean = values.isEmpty()

    fun clear() {
        values.clear()
    }

    fun push(value: T) {
        values.add(value)
    }

    fun pop(): T {
        return values.removeLast()
    }

}

class BrainfuckCode {

    @Deprecated("")
    lateinit var source: CodeRetriever

    var rootTree: SyntaxTree? = null
    val enteredTrees: Stack<SyntaxTreePosition> = Stack()
    var positionInSyntax: Int = 0

    val currentTree: SyntaxTreePosition get() = enteredTrees.peek()

    fun setRootTree(tree: SyntaxTree) {
        this.rootTree = tree;
        resetIndex()
    }

    val currentSyntax: Syntax? get() {
        val tree = currentTree
        return tree.current
    }

    fun getSyntaxIndex(): Int {
        return currentTree.currentIndex
    }

    fun isFinished(): Boolean {
        val lastInLastTree = enteredTrees.size == 1 &&
            currentTree.currentIndex == currentTree.size
        return lastInLastTree || enteredTrees.isEmpty()
    }

    fun resetIndex() {
        enteredTrees.clear()
        val root = rootTree
        if (root != null) {
            enteredTrees.push(SyntaxTreePosition(root))
        }
    }

    fun hasMoreCommands(): Boolean = !isFinished()

    fun getCommandCount(): Int {
        val root = rootTree ?: throw IllegalStateException("Root tree not set")
        val lastToken = root.tokens.last()
        return lastToken!!.info.position + lastToken.info.length
    }

    fun getCommandIndex(): Int {
        if (enteredTrees.isEmpty()) {
            // No more commands to run so we are finished
            return getCommandCount()
        }
        val syntax = currentSyntax ?: return getCommandCount()
        val token = syntax.tokens.get(positionInSyntax)
        return token!!.info.position
    }

}

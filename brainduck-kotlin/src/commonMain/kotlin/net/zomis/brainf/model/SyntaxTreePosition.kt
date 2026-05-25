package net.zomis.brainf.model

import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.ast.tree.SyntaxTree

class SyntaxTreePosition(
    val tree: SyntaxTree
) {

    private var iterator = tree.iterator()
    var current: Syntax? = null
        private set
    var currentIndex = 0
        private set

    init {
        stepForward()
    }

    fun iteratorCopy(): ListIterator<Syntax> {
        return tree.iteratorAt(currentIndex);
    }

    fun stepForward() {
        currentIndex = iterator.nextIndex()
        current = if (iterator.hasNext()) iterator.next() else null
    }

    val size: Int get() = tree.syntax.size

    fun restart() {
        this.iterator = tree.iterator()
        stepForward()
    }

}

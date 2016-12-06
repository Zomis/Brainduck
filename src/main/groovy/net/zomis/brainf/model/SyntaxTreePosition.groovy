package net.zomis.brainf.model

import groovy.transform.CompileStatic
import net.zomis.brainf.model.ast.tree.Syntax
import net.zomis.brainf.model.ast.tree.SyntaxTree

@CompileStatic
class SyntaxTreePosition {

    private final SyntaxTree tree;
    private ListIterator<Syntax> iterator;
    private Syntax current;
    private int currentIndex;

    SyntaxTreePosition(SyntaxTree tree) {
        this.tree = tree
        this.iterator = tree.iterator()
        stepForward()
    }

    public void stepForward() {
        currentIndex = iterator.nextIndex()
        current = iterator.hasNext() ? iterator.next() : null
    }

    public Syntax getCurrent() {
        return current;
    }

    public SyntaxTree getTree() {
        return tree;
    }

    public int size() {
        return tree.syntax.size()
    }

    public int getCurrentIndex() {
        return currentIndex
    }


}

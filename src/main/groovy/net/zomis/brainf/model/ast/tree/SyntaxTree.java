package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class SyntaxTree extends Syntax implements Iterable<Syntax> {

    public List<Syntax> syntax = new ArrayList<>();

    @Override
    public void perform(BrainfuckRunner runner) {
        throw new IllegalStateException("Cannot perform a full syntax tree at once");
    }

    @Override
    public ListIterator<Syntax> iterator() {
        return syntax.listIterator();
    }

    @Override
    public String toString() {
        return "SyntaxTree{" +
                "syntax=" + syntax +
                '}';
    }

}

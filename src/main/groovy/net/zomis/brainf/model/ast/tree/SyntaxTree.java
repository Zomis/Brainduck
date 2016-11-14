package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;

import java.util.ArrayList;
import java.util.List;

public class SyntaxTree extends Syntax {

    public List<Syntax> syntax = new ArrayList<>();

    @Override
    public void perform(BrainfuckRunner runner) {

    }

    @Override
    public String toString() {
        return "SyntaxTree{" +
                "syntax=" + syntax +
                '}';
    }

}

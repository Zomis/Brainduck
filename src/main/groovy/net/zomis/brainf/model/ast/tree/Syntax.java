package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;
import net.zomis.brainf.model.ast.Token;

import java.util.ArrayList;
import java.util.List;

public abstract class Syntax {

    private final List<Token> tokens = new ArrayList<>();

    public List<Token> getTokens() {
        return tokens;
    }

    public abstract void perform(BrainfuckRunner runner);

}

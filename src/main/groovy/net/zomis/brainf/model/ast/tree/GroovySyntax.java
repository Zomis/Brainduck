package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;
import net.zomis.brainf.model.ast.GroovyToken;

public class GroovySyntax extends Syntax {

    private final GroovyToken token;

    public GroovySyntax(GroovyToken token) {
        this.token = token;
    }

    @Override
    public void perform(BrainfuckRunner runner) {

    }
}

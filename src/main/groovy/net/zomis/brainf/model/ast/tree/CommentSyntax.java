package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;
import net.zomis.brainf.model.ast.CommentToken;

public class CommentSyntax extends Syntax {

    private final CommentToken token;

    public CommentSyntax(CommentToken token) {
        this.token = token;
    }

    @Override
    public void perform(BrainfuckRunner runner) {

    }

}

package net.zomis.brainf.model.ast.tree;

import net.zomis.brainf.model.BrainfuckRunner;
import net.zomis.brainf.model.ast.CommentToken;

class CommentSyntax(private val token: CommentToken) : Syntax() {

    override fun perform(runner: BrainfuckRunner) {
    }

    override fun toString(): String {
        return "CommentSyntax{" + token.text + '}';
    }

}

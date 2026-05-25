package net.zomis.brainf.model.ast.transform;

import net.zomis.brainf.model.BrainfuckRunner;
import net.zomis.brainf.model.ast.tree.Syntax;

public class OutSyntax extends Syntax {

    private final String text;

    public OutSyntax(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public void perform(BrainfuckRunner runner) {
        for (int i = 0; i < text.length(); i++) {
            runner.appendOutput(text.charAt(i));
        }
    }

}

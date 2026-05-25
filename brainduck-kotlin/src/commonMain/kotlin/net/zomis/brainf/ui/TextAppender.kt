package net.zomis.brainf.ui

import javafx.application.Platform
import javafx.scene.control.TextArea
import net.zomis.brainf.model.BrainfuckOutput

class TextAppender implements BrainfuckOutput {
    private final TextArea textArea

    TextAppender(TextArea textArea) {
        this.textArea = textArea
    }

    @Override
    void write(char value) {
        Platform.runLater({ textArea.appendText(String.valueOf(value)) })
    }

}

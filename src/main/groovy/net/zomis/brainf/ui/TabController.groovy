package net.zomis.brainf.ui

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ListView
import javafx.scene.control.TextArea
import javafx.scene.layout.AnchorPane
import javafx.stage.Stage
import net.zomis.brainf.BrainF
import net.zomis.brainf.model.BrainfuckRunner
import org.fxmisc.richtext.CodeArea
import org.fxmisc.richtext.LineNumberFactory

class TabController implements Initializable {

    @FXML ListView<String> memoryList
    @FXML AnchorPane codePane
    @FXML TextArea output

    Stage stage;

    CodeArea codeArea;
    final BrainfuckRunner brain = BrainF.createUsingSystemInputWithMemorySize(0x1000);

    private void update() {
        codeArea.selectRange(brain.getCode().getCommandIndex(), brain.getCode().getCommandIndex() + 1);
        output.setText(brain.getOutput());

        for (int i = 0; i < brain.getMemory().getMemorySize(); i++) {
            this.memoryList.getItems().set(i, memoryText(i));
        }
    }

    @Override
    void initialize(URL location, ResourceBundle resources) {
		for (int i = 0; i < brain.getMemory().getMemorySize(); i++) {
			memoryList.getItems().add("");
		}
        this.codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        codeArea.replaceText(0, 0, GroovyRead.read("fizzbuzz.bf"));
        codeArea.setOnKeyReleased({
            stage.setTitle(String.format("BrainDuck pos %d col %d", codeArea.getCaretPosition(), codeArea.getCaretColumn()));
        });
        codeArea.setPrefWidth(600);
        codeArea.setPrefHeight(600);
        AnchorPane.setTopAnchor(codeArea, 0d);
        AnchorPane.setRightAnchor(codeArea, 0d);
        AnchorPane.setLeftAnchor(codeArea, 0d);
        AnchorPane.setBottomAnchor(codeArea, 0d);
        codePane.getChildren().add(codeArea);

    }

    private String memoryText(int i) {
        int value = brain.getMemory().getMemory(i);
        char ch = (char) (value < 0 ? 256 + value : value);
        return Integer.toString(i, 16) + "\t" + value + "\t" + String.valueOf(ch).trim() + "\t" + (brain.getMemory().getMemoryIndex() == i ? "x" : "");
    }

}

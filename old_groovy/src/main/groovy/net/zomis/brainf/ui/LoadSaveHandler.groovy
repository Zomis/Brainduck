package net.zomis.brainf.ui

import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.control.Tab
import javafx.stage.FileChooser
import javafx.stage.Window

/**
 * Class for handling loading and saving
 */
class LoadSaveHandler {

    private final FileChooser dialog = new FileChooser();
    private final Tab tab
    private final TabController controller
    boolean modified
    File file

    public LoadSaveHandler(TabController controller, Tab tab) {
        this.controller = controller
        this.tab = tab
    }

    boolean save(Window window, String code) {
        if (file == null) {
            return saveAs(window, code)
        } else {
            file.text = code
            notModified()
            return true
        }
    }

    boolean saveAs(Window window, String code) {
        File file = dialog.showSaveDialog(window);
        if (file != null) {
            this.file = file
            file.text = code
            tab.text = file.getName()
            notModified()
        }
        return file != null
    }

    void notModified() {
        if (modified && tab.text.startsWith('*')) {
            tab.text = tab.text.substring(1);
        }
        modified = false
    }

    boolean closeRequest(Window window, String code) {
        if (!modified) {
            return true
        }

        String realName = tab.text
        if (realName.startsWith('*')) {
            realName = realName.substring(1)
        }
        ButtonType result = new Alert(Alert.AlertType.CONFIRMATION,
            "'$realName' has been modified. Do you want to save file before exiting?",
            ButtonType.YES, ButtonType.NO, ButtonType.CANCEL).showAndWait().orElse(ButtonType.CANCEL);

        if (result == ButtonType.CANCEL) {
            return false
        }
        if (result == ButtonType.YES) {
            return save(window, code)
        }
        return true
    }

    void modified() {
        if (!modified) {
            tab.text = '*' + tab.text;
        }
        modified = true
    }
}

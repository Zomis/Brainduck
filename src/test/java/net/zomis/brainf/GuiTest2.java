package net.zomis.brainf;

import static org.junit.Assert.*;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import net.zomis.brainf.ui.MainController;

import org.junit.Test;
import org.loadui.testfx.GuiTest;

public class GuiTest2 extends GuiTest {

	@Override
	protected Parent getRootNode() {
		
        Parent root;
		try {
			root = FXMLLoader.load(MainController.class.getResource("brainf.fxml"));
		}
		catch (IOException e) {
			return null;
		}
		
		return root;
	}

	@Test
	public void fds() {
		
		BrainFDebug.main(new String[0]);
		
		
		assertNotNull(stage);
	}
	
}

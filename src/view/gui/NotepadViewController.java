package view.gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.entities.TextTab;
import model.utils.Constants;

public class NotepadViewController implements Initializable {
	@FXML private Stage stage;
	
	@FXML private TabPane tabPane;
	@FXML private Tab emptyTab;
	@FXML private SingleSelectionModel<Tab> selectionModel;
	
	FileChooser fileLoader = new FileChooser();
	FileChooser fileSaver = new FileChooser();

	@Override public void initialize(URL url, ResourceBundle rb) {
		fileLoader.setTitle("Abrir");
		fileLoader.setInitialDirectory(new File("C:\\Users\\Mateus\\Documents"));
		fileLoader.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo de texto", "*.txt"));

		fileSaver.setTitle("Salvar como");
		fileSaver.setInitialDirectory(new File("C:\\Users\\Mateus\\Documents"));
		fileSaver.setInitialFileName("Texto");
		fileSaver.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivo de texto", "*.txt"));
		
		selectionModel = tabPane.getSelectionModel();
	}
	
	private void handleHotKeys(KeyEvent event) {
		if(Constants.NEW_COMB.match(event)) handleNewTabs();
		else if(Constants.OPEN_COMB.match(event)) handleOpenAction();
	}

	@FXML public void handleOpenAction() {
		File file = fileLoader.showOpenDialog(stage);
		if(file != null) {
			fileLoader.setInitialDirectory(file.getParentFile());
			System.out.println(file.getAbsolutePath());
		}
	}

	@FXML public void handleSaveAsAction() {
		File file = fileSaver.showSaveDialog(stage);
		if(file != null) {
			fileSaver.setInitialDirectory(file.getParentFile());
			System.out.println(file.getAbsolutePath());
		}
	}
	
	@FXML public void handleNewTabs() {
		emptyTab = new TextTab("untitled");
		tabPane.getTabs().add(emptyTab);
		selectionModel.selectLast();
	}
	
	@FXML public void handleCloseTabs() {
		Tab closeTab = selectionModel.getSelectedItem();
		if(closeTab != null) tabPane.getTabs().remove(closeTab);
	}
	
	@FXML public void closeApplication() {
		System.exit(0);
	}
	
	public void stageSceneSetters(Stage stage,Scene scene) {
		this.stage = stage;
		scene.setOnKeyPressed(event -> {
			handleHotKeys(event);
		});
	}
}
package view.gui.utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Dialogs {
	public static ButtonType buttonSalvar;
	public static ButtonType buttonCancelar;
	public static ButtonType buttonDontSave;

	public static Optional<ButtonType> showSaveAlert(String title) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Notepad");
		alert.setContentText(String.format("Deseja salvar as alterações em %s?", title));
		alert.setHeaderText(null);

		buttonSalvar = new ButtonType("Salvar", ButtonData.RIGHT);
		buttonDontSave = new ButtonType("Não Salvar", ButtonData.RIGHT);
		buttonCancelar = new ButtonType("Cancelar", ButtonData.RIGHT);

		alert.getButtonTypes().clear();
		alert.getButtonTypes().addAll(buttonSalvar, buttonDontSave, buttonCancelar);

		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("/view/images/Notepad.png"));

		return alert.showAndWait();
	}
}
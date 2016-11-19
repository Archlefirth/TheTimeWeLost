package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import main.Game;

import java.io.IOException;
import java.util.Optional;

public class MenuBarController implements MainController {
    private Game game;

    @FXML
    private void handleCloseMenuItem() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit the game?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    private void handleHomeMenuItem() throws IOException {
        game.showStartScreen();
    }

    @FXML
    private void handleViewLevelMenuItem() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("View Level");
        alert.setHeaderText("View map/level based on the narrative");
        Optional<ButtonType> result = alert.showAndWait();
    }

    @FXML
    private void handleHelpMenuItem() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Instruction for the game.");
        Optional<ButtonType> result = alert.showAndWait();
    }

    @FXML
    private void handleAboutMenuItem() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("This is a Project 5 for LMC2700." +
                "\n\nCreated by:" +
                "\nAhJin Noh" +
                "\nVikram Kumar" +
                "\nOlivia Murk Kaya" +
                "\nPaige Greenfield" +
                "\n\n Instructor: Yanni Loukissas");
        Optional<ButtonType> result = alert.showAndWait();
    }


    @Override
    public void setMainApp(Game game) {
        this.game = game;
    }
}

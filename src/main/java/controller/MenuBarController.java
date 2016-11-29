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

//    @FXML
//    private void handleViewLevelMenuItem() {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("View Level");
//        alert.setHeaderText("View map/level based on the narrative");
//        Optional<ButtonType> result = alert.showAndWait();
//    }

    @FXML
    private void handleHelpMenuItem() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("How to Play");
        alert.setHeaderText(
                "Work together to enter the correct pattern\n" +
                "Press the corresponding keys on the keyboard to input a pattern\n" +
                "Enter the right pattern before the time runs out and advance a level\n" +
                "Complete levels and advance through the ages to get home\n\n" +

                "Enter a pattern incorrectly and lose a life\n" +
                "Lose all lives and the game is over\n" +
                "Advance an age and get bonus time and another life\n\n" +

                "How far can you go?"
        );
        Optional<ButtonType> result = alert.showAndWait();
    }

    @FXML
    private void handleAboutMenuItem() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("This is a project made for LMC2700 at Georgia Tech." +
                "\n\nCreated by:" +
                "\nAhJin Noh" +
                "\nVikram Kumar" +
                "\nOlivia Murk Kaya" +
                "\nPaige Greenfield" +
                "\n\nInstructors: Yanni Loukissas");
        Optional<ButtonType> result = alert.showAndWait();
    }


    @Override
    public void setMainApp(Game game) {
        this.game = game;
    }
}

package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.Game;

import java.io.IOException;
import java.util.Optional;

public class StartScreenController implements MainController{
    Game game;
    @FXML private TextField player1;
    @FXML private TextField player2;
    @FXML
    private void handleStartButton() throws IOException {
        if (player1.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Player 1");
            alert.setHeaderText("Please put player 1's name.");
            Optional<ButtonType> result = alert.showAndWait();
        } else if (player2.getText().length() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Player 2");
            alert.setHeaderText("Please put player 2's name.");
            Optional<ButtonType> result = alert.showAndWait();
        } else {
            MainGameController.setPlayers(player1.getText(), player2.getText());
            game.showMainGame();
        }
    }

    //TODO make a instruction page
    @FXML
    private void handleInstructionButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Implementation needed");
        alert.setHeaderText("This feature has not been implemented yet.");
        Optional<ButtonType> result = alert.showAndWait();
    }

    @Override
    public void setMainApp(Game game) {
        this.game = game;
    }
}

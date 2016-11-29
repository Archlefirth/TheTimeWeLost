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
        // if the users doesn't enter names set to default
        // change these names to the names of the actual time traveller characters
        if (player1.getText().length() == 0){
            player1.setText("Lanni Youkissas");
        }
        if (player2.getText().length() == 0){
            player2.setText("Freya");
        }
        MainGameController.setPlayers(player1.getText(), player2.getText());
        SwitchSceneController.setPlayers(player1.getText(), player2.getText());
        game.switchScene();
    }

    //TODO make a instruction page
    @FXML
    private void handleInstructionButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("HOW TO PLAY");
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

    @Override
    public void setMainApp(Game game) {
        this.game = game;
    }
}

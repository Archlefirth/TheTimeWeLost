package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import main.Game;
import java.io.IOException;
import java.net.URL;

public class StartScreenController implements MainController{
    private Game game;
    @FXML private TextField player1;
    @FXML private TextField player2;
    @FXML private GridPane instructionPane;
    private AudioClip backgroundMusic;


    @FXML
    private void initialize() {
        instructionPane.setVisible(false);
        URL resource = getClass().getResource("/sound/background/Main.wav");
        backgroundMusic = new AudioClip(resource.toString());
        backgroundMusic.setCycleCount(1000);
        backgroundMusic.play();
    }

    @FXML
    private void handleStartButton() throws IOException {
        // if the users doesn't enter names set to default
        // change these names to the names of the actual time traveller characters
        if (player1.getText().length() == 0){
            player1.setText("Lanni Youkissas");
        }
        if (player2.getText().length() == 0){
            player2.setText("Freya Lindholm");
        }
        MainGameController.setPlayers(player1.getText(), player2.getText());
        SwitchSceneController.setPlayers(player1.getText(), player2.getText());
        MainGameController.setLevel(1, 3);
        backgroundMusic.stop();
        playSoundFX("Button.mp3");
        game.switchScene();
    }


    @FXML
    private void handleBackToMain() {
        playSoundFX("Button.mp3");
        instructionPane.setVisible(false);
    }

    @FXML
    private void handleHowToPlay() {
        playSoundFX("Button.mp3");
        instructionPane.setVisible(true);
    }

    /*
    * plays the sound effect for buttons etc
    * @param filename path to the sound file /sound/ ...
    */
    private void playSoundFX(String filename) {
        URL resource = getClass().getResource("/sound/FX/" + filename);
        AudioClip soundFX = new AudioClip(resource.toString());
        soundFX.play();
    }

    @Override
    public void setMainApp(Game game) {
        this.game = game;
    }
}

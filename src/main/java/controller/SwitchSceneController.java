package controller;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.Game;
import model.Dialogue;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class SwitchSceneController implements MainController{
    private Game game;
    @FXML private StackPane leftDoor;
    @FXML private StackPane rightDoor;
    @FXML private Text text;
    @FXML private Button next;
    @FXML private Button skip;
    @FXML private ImageView img;
    @FXML private ImageView character;
    private Image character1 = new Image("/img/character/Lanni-Head.png");
    private Image character2 = new Image("/img/character/Freya-Head.png");
    private static String player1;
    private static String player2;
    private ArrayList<String>[] lineList;
    private static int stage;
    private int lineNum;
    private AudioClip backgroundMusic;

    @FXML
    private void initialize () {
        next.setDisable(false);
        character.setVisible(false);
        animateDoor(0, -500, leftDoor);
        animateDoor(0, 500, rightDoor);
        Dialogue dialogue = new Dialogue(player1, player2);
        lineList = dialogue.getDialogue();
        if (stage < 6) {
            Image stageImg = new Image("/img/" + stage + ".gif");
            img.setImage(stageImg);
        }
        text.setTranslateX(0);
        text.setText(readLine(stage, lineNum));
        playBackgroundMusic(stage);
    }

    /*
     * sets the current user
     * @param p1 first player
     * @param p2 second player
     */
    public static void setPlayers(String p1, String p2) {
        player1 = p1;
        player2 = p2;
    }

    /*
     * animate the doors
     * @param from location to translate from
     * @param to location to translate to
     * @param pane pane to animate
     */
    @FXML
    private Transition animateDoor(int from, int to, Pane pane){
        TranslateTransition transition
                = new TranslateTransition(Duration.seconds(1.75), pane);
        transition.setFromX(from);
        transition.setToX(to);
        transition.play();
        playSoundFX("Door.wav");
        return transition;
    }

    /*
     * clicking next will update the dialogue in the text box
     * clicking skip will continue to the main game screen
    */
    @FXML
    private void handleButtonClicked(ActionEvent event) throws IOException {
        if (event.getSource() == next) {
            if (lineNum < lineList[stage].size() - 1) {
                lineNum++;
                String currentLine = readLine(stage, lineNum);
                text.setText(currentLine);
                text.setWrappingWidth(750);
                if (currentLine.contains(player1 + ": ")) {
                    character.setVisible(true);
                    text.setTranslateX(170);
                    character.setImage(character1);
                } else if ( currentLine.contains(player2 + ": ")){
                    character.setVisible(true);
                    text.setTranslateX(170);
                    character.setImage(character2);
                } else {
                    character.setVisible(false);
                    text.setWrappingWidth(930);
                    text.setTranslateX(0);
                }
            } else {
                next.setDisable(true);
            }
        } else if (event.getSource() == skip) {
            Transition transition = animateDoor(-500, 0, leftDoor);
            animateDoor(500, 0, rightDoor);
            transition.setOnFinished(e -> {
                try {
                    backgroundMusic.stop();
                    game.showMainGame();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            });
        }
        playSoundFX("Button.mp3");
    }

    /*
     * read the lines to display for the dialogue box.
     * @param stage current stage of the game
     * @param lineNum current line to display
     */
    private String readLine(int stage, int lineNum){
        return lineList[stage].get(lineNum);
    }

    /*
    * plays the sound effect for buttons
    * @param filename name of audio to play
    */
    private void playSoundFX(String filename) {
        URL resource = getClass().getResource("/sound/FX/" + filename);
        AudioClip soundFX = new AudioClip(resource.toString());
        soundFX.play();
    }

    /*
    * plays theme song for each stage
    * @param stage theme song for the stage
    */
    private void playBackgroundMusic(int stage) {
        URL resource = getClass().getResource("/sound/background/" + stage + ".wav");
        backgroundMusic = new AudioClip(resource.toString());
        backgroundMusic.setCycleCount(1000);
        backgroundMusic.play();
    }

    /*
     * sets the current stage of the year
     */
    public static void setStage(int stg) {
        stage = stg;
    }

    @Override
    public void setMainApp(Game game) {
        this.game = game;
    }
}

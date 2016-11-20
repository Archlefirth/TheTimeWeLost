package controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.Game;
import model.Dialogue;

import java.io.*;
import java.util.ArrayList;

public class SwitchSceneController implements MainController{
    Game game;
    @FXML private StackPane leftDoor;
    @FXML private StackPane rightDoor;
    @FXML private Text playerName;
    @FXML private Text text;
    @FXML private Button next;
    @FXML private Button skip;
    private static String player1;
    private static String player2;
    private Dialogue dialogue;
    ArrayList<String>[] lineList;
    String currentLine;
    private static int stage;
    private int lineNum;

    @FXML
    private void initialize () throws IOException {
        next.setDisable(false);
        animateDoor("LEFT", leftDoor);
        animateDoor("RIGHT", rightDoor);
        dialogue = new Dialogue(player1, player2);
        lineList = dialogue.getDialogue();
        text.setText(readLine(stage, lineNum));
        lineNum++;
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
     * @param direction direction to slide the doors
     * @param pane pane to animate
     */
    @FXML
    private void animateDoor(String direction, Pane pane){
        TranslateTransition transition
                = new TranslateTransition(Duration.seconds(2), pane);
        if (direction.equals("LEFT")) {
            transition.setFromX(0);
            transition.setToX(-500);
        } else if (direction.equals("RIGHT")) {
            transition.setFromX(0);
            transition.setToX(500);
        }
        transition.play();
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
                text.setText(readLine(stage, lineNum));
            } else {
                next.setDisable(true);
            }
        } else if (event.getSource() == skip) {
            game.showMainGame();
        }
    }

    /*
     * read the lines to display for the dialogue box.
     * @param stage current stage of the game
     * @param lineNum current line to display
     */
    private String readLine(int stage, int lineNum){
        currentLine= lineList[stage].get(lineNum);
        return currentLine;
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

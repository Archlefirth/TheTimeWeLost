package controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.Game;

import java.io.IOException;

import static java.util.Arrays.sort;

public class GameOverController implements MainController {
    private Game game;
    @FXML private Text firstPlayers;
    @FXML private Text secondPlayers;
    @FXML private Text thirdPlayers;
    @FXML private Text firstLevel;
    @FXML private Text secondLevel;
    @FXML private Text thirdLevel;
    @FXML private ImageView background;
    private static Image img = new Image("/img/gameover.png");
    private static int level1, level2, level3;
    private static int level;
    private static String players1;
    private static String players2;
    private static String players;

    /*
     * initialize the Game Over screen.
     */
    @FXML
    private void initialize(){
        background.setImage(img);
        setChart();
    }

    /*
     * gets the highest level from the current players
     *
     * @param p1 player 1
     * @param p2 player 2
     * @param inLevel current level
     */
    public static void setLevel(String p, int inLevel) {
        players = p;
        level = inLevel;
    }

    @FXML
    public static void setBackground(String result) {
        if (result.equals("gameover")) {
            img = new Image ("/img/gameover.png");
        } else {
            img = new Image("/img/won.png");
        }

    }
    /*
     * updates the chart
     */
    @FXML
    private void setChart() {
        int[] levelArr = {level, level1, level2, level3};
        String a = "";
        for (int aLevelArr : levelArr) {
            a = a + ", " + aLevelArr;
        }
        sort(levelArr);

        String players3;
        if (levelArr[3] == level) {
            thirdLevel.setText(Integer.toString(level2));
            thirdPlayers.setText(players2);
            level3 = level2;
            secondLevel.setText(Integer.toString(level1));
            secondPlayers.setText(players1);
            players2 = players1;
            level2 = level1;
            if (level >= 31) {
                firstLevel.setText("Cleared");
            } else {
                firstLevel.setText(Integer.toString(level));
            }
            firstPlayers.setText(players);
            players1 = players;
            level1 = level;

        } else if (levelArr[2] == level) {
            thirdLevel.setText(Integer.toString(level2));
            thirdPlayers.setText(players2);
            level3 = level2;
            secondLevel.setText(Integer.toString(level));
            secondPlayers.setText(players);
            players2 = players;
            level2 = level;
        } else if (levelArr[1] == level) {
            thirdLevel.setText(Integer.toString(level));
            thirdPlayers.setText(players);
            level3 = level;

        }
    }

    @FXML
    private void handleStartOverButton() throws IOException {
        MainGameController.setLevel(1, 3);
        game.showStartScreen();
    }

    @Override
    public void setMainApp(Game game) {
        this.game = game;
    }
}

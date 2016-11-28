package controller;

import javafx.fxml.FXML;
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
    @FXML private Text header;
    private static String header_text = "Game Over";
    private static int level1, level2, level3;
    private static int level;
    private static String players1, players2, players3;
    private static String players;

    /*
     * initialize the Game Over screen.
     */
    @FXML
    private void initialize(){
        header.setText(header_text);
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
    public static void setHeader(String h) {
        header_text = h;
    }
    /*
     * updates the chart
     */
    @FXML
    private void setChart() {
        int[] levelArr = {level, level1, level2, level3};
        String a = "";
        for (int i = 0; i < levelArr.length; i++) {
            a = a + ", " + levelArr[i];
        }
        sort(levelArr);

        if (levelArr[3] == level) {
            thirdLevel.setText(Integer.toString(level2));
            thirdPlayers.setText(players2);
            players3 = players2;
            level3 = level2;
            secondLevel.setText(Integer.toString(level1));
            secondPlayers.setText(players1);
            players2 = players1;
            level2 = level1;
            firstLevel.setText(Integer.toString(level));
            firstPlayers.setText(players);
            players1 = players;
            level1 = level;
        } else if (levelArr[2] == level) {
            thirdLevel.setText(Integer.toString(level2));
            thirdPlayers.setText(players2);
            players3 = players2;
            level3 = level2;
            secondLevel.setText(Integer.toString(level));
            secondPlayers.setText(players);
            players2 = players;
            level2 = level;
        } else if (levelArr[1] == level) {
            thirdLevel.setText(Integer.toString(level));
            thirdPlayers.setText(players);
            players3 = players;
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

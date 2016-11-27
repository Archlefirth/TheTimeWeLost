package controller;

import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import main.Game;
import model.PatternChar;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class MainGameController implements MainController{
    private Game game;
    private boolean isPatternVisible = true;
    private static int level = 1;
    private static int life = 3;
    private static int stage;
    private static int patternLength; //initial pattern length; will increase by level
    private static int givenTime; // the starting time for each level given in seconds
    private Integer timeSeconds; // to update the text label
    private static String player1str;
    private static String player2str;
    private char[] player1keys = {'A', 'S', 'D', 'W'};
    private char[] player2keys = {'K', 'K', 'L', 'I'};
    private PatternChar[] pattern;
    private String patternToStr;
    private Random random = new Random();
    private TextField[] inputTextField;
    private static Timeline timeline = new Timeline();
    private Image stageImg;
    @FXML private Text lifeText;
    @FXML private Text levelText;
    @FXML private Text timeText;
    @FXML private Button enter;
    @FXML private Text player1;
    @FXML private Text player2;
    @FXML private GridPane patternGrid;
    @FXML private GridPane playerPatternGrid;
    @FXML private Text info;
    @FXML private ImageView img;

    /**
     * Initializes the Main Game screen.
     */
    @FXML
    private void initialize() {
        stageImg = new Image("/img/timeline/" + stage + ".png");
        img.setImage(stageImg);
        // default time is 10 seconds, increase by 10 bonus seconds each level as the game gets harder
        givenTime = 10 + ((level / 5) * 10);
        randomizePattern();
        showPattern();
        levelText.setText("Level: " + level);
        lifeText.setText("Life: " + life);
        timeText.setText("" + givenTime);
        player1.setText("Player 1: " + player1str);
        player1.setStyle("-fx-fill: #0091EA");
        player2. setText("Player 2: " + player2str);
        player2.setStyle("-fx-fill: #EF5350");
    }

    /*
    * runs the timer for the given time when pattern disappears.
    * When time hits 0 sec, it will automatically match the current user input with the original pattern
    * @param time the time value to be set
    */
    @FXML
    private void bindToTime(int time) {
        timeline = new Timeline();
        timeSeconds = time;
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                timeSeconds--;
                                timeText.setText("" + timeSeconds.toString());
                                if (timeSeconds.intValue() <= 3) {
                                    timeText.setStyle("-fx-fill:#FF7043");
                                }
                                if (timeSeconds.intValue() <= 0) {
                                    try {
                                        matchAnswers();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /*
     * shows the original pattern on the screen.
     */
    @FXML
    private void showPattern() {
        System.out.println("Current pattern length: " + pattern.length);
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHalignment(HPos.CENTER);
        cc.setPercentWidth(100.0 / pattern.length);
        showPlayerPattern(cc);
        for (int i = 0; i < pattern.length; i++) {
            patternGrid.getColumnConstraints().add(i, cc);
        }
        for (int i = 0; i < pattern.length; i++) {
            Text character = new Text();
            character.setText(pattern[i].getCharacter() + "");
            if (pattern[i].getPlayer().equals("1")) {
                character.setStyle("-fx-fill: #0091EA; -fx-font-size: 40px;");
            } else {
                character.setStyle("-fx-fill: #EF5350; -fx-font-size: 40px;");
            }
            character.setWrappingWidth(1000.0 / pattern.length);
            character.setTextAlignment(TextAlignment.CENTER);
            patternGrid.add(character, i, 0);
        }
        FadeTransition transition
                = new FadeTransition(Duration.seconds(3), patternGrid); // original pattern visible for 3 seconds
        transition.setFromValue(100);
        transition.setToValue(0);
        transition.play();
        transition.onFinishedProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                patternGrid.setVisible(false);
                disablePlayerInput(false);
                info.setText("Please type in the pattern.");
                bindToTime(givenTime);
            }
        });
    }

    /*
    * shows the player's input pattern on the screen as the user types the key.
    */
    @FXML
    private void showPlayerPattern(ColumnConstraints cc) {
        inputTextField = new TextField[pattern.length];
        for (int i = 0; i < pattern.length; i++) {
            TextField field = new TextField();
            field.setAlignment(Pos.CENTER);
            playerPatternGrid.getColumnConstraints().add(i, cc);
            playerPatternGrid.addColumn(i, field);
            playerPatternGrid.setHgap(20);
            field.setMaxHeight(100);
            final int j = i; // for focus property listener only
            field.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue) {
                        if (pattern[j].getPlayer().equals("1")) {
                            field.setStyle("-fx-border-width: 4; -fx-border-color:#00BCD4");
                        } else {
                            field.setStyle("-fx-border-width: 4; -fx-border-color:#FF7043");
                        }
                    } else {
                        field.setStyle("-fx-border-size: 0");
                    }
                }
            });
            if (pattern[i].getPlayer() == "1") {
                field.setId("text-field-blue");
            } else {
                field.setId("text-field-red");
            }
            inputTextField[i] = field;
        }
        disablePlayerInput(true);
        playerPatternGrid.setPadding(new Insets(0, 20, 0, 20));
        for (int i = 0; i < inputTextField.length; i++) {
            final int j = i;
            inputTextField[i].setOnKeyReleased((KeyEvent e) -> {
                if (e.getCode().isLetterKey()) {    // only accept letter keys for now
                    inputTextField[j].setText(e.getText());
                    if ((j + 1) < inputTextField.length) {
                        inputTextField[j + 1].requestFocus(); //to only take one char for the input field
                    }
                } else if (e.getCode().equals(KeyCode.ENTER)){ //hitting enter key will match the answers
                    try {
                        inputTextField[0].requestFocus();
                        matchAnswers();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }else {
                    inputTextField[j].setText("");
                }
            });
        }
    }

    /*
     * disable the input text fields until the given pattern disappears - to avoid cheating!
     * @param option true if set disabled
     */
    @FXML
    private void disablePlayerInput(boolean option) {
        for (int i = 0; i < inputTextField.length; i++) {
            inputTextField[i].setDisable(option);
        }
        enter.setDisable(option);
    }

    /*
     * Temporary method to build our logic for pattern matching.
     * this will create a random pattern based on the level of the stage.
     * the pattern will be generated with characters alternating player1's keys and player2's keys.
     */
    private void randomizePattern() {
        if (level < 5) { //stage 1
            patternLength = 4;
        } else if (level < 10) { //stage 2
            patternLength = 6;
        } else if (level < 15) { //stage 3
            patternLength   = 8;
        } else if (level < 20) { //stage 4
            patternLength = 10;
        } else if (level < 25) { //stage 5;
            patternLength = 15;
        } else {
            patternLength = 15;
        }
        pattern = new PatternChar[patternLength];
        int count = 1;
        for (int i = 0; i <  patternLength; i++) {
            if (count % 2 == 1) {
                pattern[i] = new PatternChar(player1keys[random.nextInt(4)], "1");
            } else {
                pattern[i] = new PatternChar(player2keys[random.nextInt(4)], "2");
            }
            //count++;
            if (level < 5) {
                count++; //simply alternate key until level 5
            } else {
                //TODO: Optimize randomizer to fix bug (below)
                count = count + random.nextInt(2);  //randomize keys after level 3... to make it harder
                // note : this will sometimes cause a bug (all pattern char generated for one user)
            }
        }
        patternToStr = "";
        for (int i = 0; i <  pattern.length; i++) {
            patternToStr = patternToStr + pattern[i].getCharacter();
        }
        System.out.println("Pattern generated : " + patternToStr); //debugging purpose
    }

    /*
     * clicking the Enter button after user types in an input.
     */
    @FXML
    private void handleEnterButton() throws IOException {
        if (enter.getText().equals("GO!")) {
            enter.setText("ENTER");
            resetPatternGrid();
            info.setText("Please wait until the pattern disappears...");
        } else {
            matchAnswers();
        }
    }

    /*
     * matches the user input with the original pattern and change the display based on it
     */
    @FXML
    private void matchAnswers() throws IOException {
        timeline.stop();
        String input = "";
        for (int i = 0; i < pattern.length; i++) {
            input = input + inputTextField[i].getText();
        }
        System.out.println("User input was : " + input);  // debugging purpose
        if (input.equalsIgnoreCase(patternToStr)) {
            for (int i = 0; i < inputTextField.length; i++) {
                inputTextField[i].setDisable(true);
            }
            patternGrid.setOpacity(100.0);
            patternGrid.setVisible(true);
            level++;
            if (level >= 5 ) { // the pattern will always be the starting value for the first 5 stages
                patternLength++;
            }
            levelText.setText("Level: " + level);
            info.setText("Correct!\nNext Level : " + level);
            enter.setText("GO!");
            timeText.setStyle("-fx-fill:#4DD0E1");
            timeText.setText("" + timeSeconds);
            if (level % 5 == 0) {
                stage++;
                life++; // bonus life every new stage
                SwitchSceneController.setStage(stage);
                game.switchScene();
            }
            timeText.setText("" + givenTime);
        } else {
            life--;
            lifeText.setText("Life: " + life);
            if (life <= 0) {
                GameOverController.setLevel((player1str + " & " + player2str), level);
                game.showGameOverScreen();
                timeline.stop();
            } else if (timeSeconds.intValue() <= 0 && life > 0){
                info.setText("Time's Up! Try Again!");
                for (int i = 0; i < inputTextField.length; i++) {
                    inputTextField[i].setText("");
                }
                timeText.setStyle("-fx-fill:#4DD0E1");
                bindToTime(givenTime);
            } else {
                info.setText("Incorrect! Please try again.");
                for (int i = 0; i < inputTextField.length; i++) {
                    inputTextField[i].setText("");
                }
                bindToTime(timeSeconds);
            }
        }
    }

    /*
     * reset the original pattern grid
     */
    private void resetPatternGrid() {
        inputTextField = new TextField[pattern.length];
        patternGrid.getChildren().clear();
        patternGrid.getColumnConstraints().clear();
        playerPatternGrid.getChildren().clear();
        playerPatternGrid.getColumnConstraints().clear();
        randomizePattern();
        showPattern();
    }

    /*
     * sets the players from from another controller
     */

    public static void setPlayers(String player1, String player2) {
        player1str = player1;
        player2str = player2;
    }

    /*
     * resets the level/life from another controller (reset)
     */
    public static void setLevel(int lvl, int lf) {
        level = lvl;
        life = lf;
    }

    /*
     * helper method to create alert screen.
     *
     * @param type type of the alert (WARNING, INFORMATION, CONFIRMATION)
     * @param title title of the alert
     * @param description description of the alert
     */
    private Alert showAlert(Alert.AlertType type, String title, String description){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(description);
        return alert;
    }

    @Override
    public void setMainApp(Game game) {
        this.game = game;
    }
}
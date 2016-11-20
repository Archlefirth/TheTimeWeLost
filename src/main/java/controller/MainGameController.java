package controller;

import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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
    private static int level;
    private static int life;
    private static int patternLength; //initial pattern length; will increase by level
    private static int givenTime; //time given in seconds
    private Integer timeSeconds; // to update the text label
    private static String player1str;
    private static String player2str;
    private char[] player1keys = {'A', 'S', 'D', 'W'};
    private char[] player2keys = {'K', 'K', 'L', 'I'};
    private PatternChar[] pattern;
    private String patternToStr; //for debugging purpose
    private Random random = new Random();
    private TextField[] inputTextField;
    private static Timeline timeline = new Timeline();
    @FXML private Text lifeText;
    @FXML private Text levelText;
    @FXML private Text timeText;
    @FXML private Button enter;
    @FXML private Text player1;
    @FXML private Text player2;
    @FXML private GridPane patternGrid;
    @FXML private GridPane playerPatternGrid;
    @FXML private Text info;

    /**
     * Initializes the Main Game screen.
     */
    @FXML
    private void initialize() {
        level = 1;
        patternLength = 4;
        life = 3;
        // default time is 10 seconds, increase slightly each level as the game gets harder
        givenTime = 10;
        //bindToTime();
        randomizePattern();
        showPattern();
        levelText.setText("Level: " + level);
        lifeText.setText("Life: " + life);
        timeText.setText("Time: " + givenTime);
        player1.setText("Player 1: " + player1str);
        player1.setStyle("-fx-fill: #EF5350");
        player2. setText("Player 2: " + player2str);
        player2.setStyle("-fx-fill: #0091EA");
    }

    /*
    * runs the timer for the given time when pattern disappears.
    * When time hits 0 sec, it will automatically match the current user input with the original pattern
    */
    @FXML
    private void bindToTime() {
        timeline = new Timeline();
        timeSeconds = givenTime;
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                timeSeconds--;
                                timeText.setText("Time: " + timeSeconds.toString());
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
                character.setStyle("-fx-fill: #EF5350; -fx-font-size: 40px;");
            } else {
                character.setStyle("-fx-fill: #0091EA; -fx-font-size: 40px;");
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
                bindToTime();
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
                            field.setStyle("-fx-border-width: 4; -fx-border-color:#FF7043");
                        } else {
                            field.setStyle("-fx-border-width: 4; -fx-border-color:#00BCD4");
                        }
                    } else {
                        field.setStyle("-fx-border-size: 0");
                    }
                }
            });
            if (pattern[i].getPlayer() == "1") {
                field.setId("text-field-red");
            } else {
                field.setId("text-field-blue");
            }
            inputTextField[i] = field;
        }
        disablePlayerInput(true);
        playerPatternGrid.setPadding(new Insets(0, 20, 0, 20));
        for (int i = 0; i < inputTextField.length; i++) {
            final int j = i;
            inputTextField[i].setOnKeyReleased((KeyEvent e) -> {
                if (e.getCode().isLetterKey() == true) {    // only accept letter keys for now
                    inputTextField[j].setText(e.getText());
                    if ((j + 1) < inputTextField.length) {
                        inputTextField[j + 1].requestFocus(); //to only take one char for the input field
                    }
                } else if (e.getCode().equals(KeyCode.ENTER)){ //hitting enter key will match the answers
                    try {
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
        pattern = new PatternChar[patternLength];
        int count = 1;
        for (int i = 0; i <  patternLength; i++) {
            if (count % 2 == 1) {
                pattern[i] = new PatternChar(player1keys[random.nextInt(4)], "1");
            } else {
                pattern[i] = new PatternChar(player2keys[random.nextInt(4)], "2");
            }
            //count++;
            if (level < 3) {
                count++; //simply alternate key until level 3
            } else {
                count = count + random.nextInt(2);  //randomize keys after level 3... to make it harder
            }
            //TODO we could either alternate OR randomize player 1 & 2's keys.
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
            //bindToTime();
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
        timeSeconds = givenTime;
        timeText.setText("Time: " + givenTime);
        String input = "";
        for (int i = 0; i < pattern.length; i++) {
            input = input + inputTextField[i].getText();
        }
        System.out.println("User input was : " + input);  // debugging purpose
        if (input.equalsIgnoreCase(patternToStr)) {
            patternGrid.setOpacity(100.0);
            patternGrid.setVisible(true);
            level++;
            patternLength++;
            levelText.setText("Level: " + level);
            info.setText("Correct!\nNext Level : " + level);
            enter.setText("GO!");
        } else {
            life--;
            lifeText.setText("Life: " + life);
            if (life <= 0) {
                GameOverController.setLevel((player1str + " & " + player2str), level);
                game.showGameOverScreen();
            } else if (timeSeconds.intValue() <= 0 && life > 0){
                info.setText("Time Up! Please try again.");
                for (int i = 0; i < inputTextField.length; i++) {
                    inputTextField[i].setText("");
                }
                bindToTime();
            } else {
                info.setText("Incorrect! Please try again.");
                for (int i = 0; i < inputTextField.length; i++) {
                    inputTextField[i].setText("");
                }
                bindToTime();
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

    public static void setPlayers(String player1, String player2) {
        player1str = player1;
        player2str = player2;
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
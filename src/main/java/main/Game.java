package main;
import controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class Game extends Application{
    private Stage mainScreen;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainScreen = primaryStage;
        showStartScreen();
    }

    /**
     * displays the screen
     *
     * @param mainScreen screen to be displayed.
     * @param FXMLFilePath fxml file to display
     * @param filename name of fxml to find
     * @throws IOException throws an exception if fxml is not found.
     */
    private void showScreen(Stage mainScreen, String FXMLFilePath, String filename) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Game.class.
                    getClassLoader().getResource(FXMLFilePath));
            Pane rootLayout = loader.load();
            MainController controller = loader.getController();
            controller.setMainApp(this);
            mainScreen.setTitle("The Time We Lost");
            Scene scene = new Scene(rootLayout);
            mainScreen.setScene(scene);
            mainScreen.show();
            mainScreen.setResizable(false);
            mainScreen.sizeToScene();
        } catch (IOException e) {
            throw new IOException("Cannot find the " + filename + ".fxml.");
        }
    }

    /**
     * sets the screen to main game screen.
     *
     * @throws IOException throws an exception if MainGame.fxml is not found.
     */
    public void showMainGame() throws IOException {
        showScreen(mainScreen,"view/MainGame.fxml", "MainGame");
    }

    /**
     * sets the screen to start main menu screen
     *
     * @throws IOException throws an exception if StartScreen.fxml is not found.
     */
    public void showStartScreen() throws IOException {
        showScreen(mainScreen,"view/StartScreen.fxml", "StartScreen");
    }

    /**
     * sets the screen to game over.
     *
     * @throws IOException throws an exception if GameOver.fxml is not found.
     */
    public void showGameOverScreen() throws IOException {
        showScreen(mainScreen,"view/GameOver.fxml", "StartScreen");
    }

    /**
     * switches the scene whenever the level is complete
     *
     * @throws IOException throws an exception if SwitchScene.fxml is not found.
     */
    public void switchScene() throws IOException {
        showScreen(mainScreen,"view/SwitchScene.fxml", "SwitchScene");
    }

    /**
     * runs the program.
     * @param args runs the program.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

package main;
import controller.MainController;
import controller.MenuBarController;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Game extends Application{
    private BorderPane rootLayout;
    private Stage mainScreen;
    private static MenuBarController menuBarController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainScreen = primaryStage;
        initRootLayout(mainScreen);
        showStartScreen();
    }

    /**
     * initializes the menu bar border pane which will show other pages as well.
     *
     * @param mainScreen screen to be displayed.
     * @throws IOException throws an exception if fxml is not found.
     */
    private void initRootLayout(Stage mainScreen) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Game.class.
                    getClassLoader().getResource("view/MenuBar.fxml"));
            rootLayout = loader.load();
            menuBarController = loader.getController();
            menuBarController.setMainApp(this);
            mainScreen.setTitle("LMC2700 Project 5");
            Scene scene = new Scene(rootLayout);
            mainScreen.setScene(scene);
            mainScreen.show();
            mainScreen.setResizable(false);
            mainScreen.sizeToScene();
        } catch (IOException e) {
            throw new IOException("Cannot find the MenuBar.fxml.");
        }
    }

    /**
     * changes the screen based on the given fxml file path.
     * @param fxmlFilePath path for the fxml file
     * @param fxmlName   name of the fxml file
     */
    private void showScreen(String fxmlFilePath,
                            String fxmlName) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Game.class.
                    getClassLoader().getResource(fxmlFilePath));
            Pane showPage = loader.load();
            rootLayout.setCenter(showPage);
            MainController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            throw new IOException("Cannot find the " + fxmlName + ".fxml.");
        }
    }

    /**
     * sets the screen to main game screen.
     *
     * @throws IOException throws an exception if MainGame.fxml is not found.
     */
    public void showMainGame() throws IOException {
        showScreen("view/MainGame.fxml", "MainGame");
    }

    /**
     * sets the screen to start main menu screen
     *
     * @throws IOException throws an exception if StartScreen.fxml is not found.
     */
    public void showStartScreen() throws IOException {
        showScreen("view/StartScreen.fxml", "StartScreen");
    }

    /**
     * sets the screen to game over.
     *
     * @throws IOException throws an exception if GameOver.fxml is not found.
     */
    public void showGameOverScreen() throws IOException {
        showScreen("view/GameOver.fxml", "GameOver");
    }

    /**
     * switches the scene whenever the level is complete
     *
     * @throws IOException throws an exception if SwitchScene.fxml is not found.
     */
    public void switchScene() throws IOException {
        showScreen("view/SwitchScene.fxml", "SwitchScene");
    }

    /**
     * runs the program.
     * @param args runs the program.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

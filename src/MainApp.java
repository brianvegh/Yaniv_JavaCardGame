import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import yaniv.controller.GameD;
// ************************************************************
// Title: Yaniv Brian Vegh Semester Project
// File: MainApp.java
// Author: Brian Vegh
// Description: Application file.
// ***********************************************************
import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initGameScene();
        GameD.newGame();
    }
    //Initializes the root layout.
    private void initGameScene() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("yaniv/view/GameScene2.fxml"));
            BorderPane gameScene = loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(gameScene);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Yaniv Card Game");
            primaryStage.setMinHeight(868);
            primaryStage.setMinWidth(1024);
            primaryStage.setMaxHeight(868);
            primaryStage.setMaxWidth(1024);
            primaryStage.setHeight(868);
            primaryStage.setWidth(1024);
            primaryStage.setResizable(true);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
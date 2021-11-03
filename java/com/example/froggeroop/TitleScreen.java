package com.example.froggeroop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Entry-point of the application and controller of the title screen scene
 */
public class TitleScreen extends Application {

    private static Stage stage;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        TitleScreen.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(TitleScreen.class.getResource("title-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Yogger");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Gets stage, useful when needed for scene changes.
     *
     * @return the stage
     */
    public static Stage getStage() {
        return stage;
    }
}

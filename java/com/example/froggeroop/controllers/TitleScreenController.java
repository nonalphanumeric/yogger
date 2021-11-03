package com.example.froggeroop.controllers;

import com.example.froggeroop.TitleScreen;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

/**
 * Controller-type module of the title screen scene.
 */
public class TitleScreenController {
    @FXML
    private Button newGame;
    @FXML
    private Button options;
    @FXML
    private Button quit;

    /**
     * Method called once after the constructor.
     * Will change the style of the view, namely, the border of the buttons.
     */
    @FXML
    void initialize() {
        newGame.setStyle("-fx-border-color: #000000; ");
        options.setStyle("-fx-border-color: #000000; ");
        quit.setStyle("-fx-border-color: #000000; ");
    }

    /**
     * Method bound to the new game JavaFX button.
     * Will make the scene change to the game scene.
     *
     * @throws IOException if the path to the fxml file is not correct
     */
    @FXML
    protected void goToNewGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TitleScreen.class.getResource("game.fxml"));

        TitleScreen.getStage().setScene(new Scene(fxmlLoader.load()));

    }

    /**
     * Method bound to the option JavaFX button.
     * Will make the scene change to the option scene.
     *
     * @throws IOException if the path to the fxml file is not correct
     */
    @FXML
    protected void goToOptions() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TitleScreen.class.getResource("options.fxml"));

        TitleScreen.getStage().setScene(new Scene(fxmlLoader.load()));

    }

    /**
     * Method bound to the quit JavaFX button.
     * Will exit the application.
     */
    @FXML
    protected void quitting() {
        Platform.exit();
        System.exit(0);
    }
}

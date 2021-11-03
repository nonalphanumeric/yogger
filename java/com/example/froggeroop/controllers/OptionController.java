package com.example.froggeroop.controllers;

import com.example.froggeroop.TitleScreen;
import com.example.froggeroop.models.OptionModel;
import com.example.froggeroop.util.Difficulty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

/**
 * Controller-type module of the option scene.
 */
public class OptionController {
    @FXML
    private RadioButton easy;
    @FXML
    private RadioButton medium;
    @FXML
    private RadioButton hard;

    @FXML
    private CheckBox infiniteMode;

    /**
     * Method called once after the constructor.
     * Helps manage the fact that only one difficulty button
     * can be toggled at once.
     */
    @FXML
    public void initialize() {
        ToggleGroup group = new ToggleGroup();
        easy.setToggleGroup(group);
        medium.setToggleGroup(group);
        hard.setToggleGroup(group);

        switch (OptionModel.getDifficulty()) {
            case EASY -> easy.setSelected(true);
            case MEDIUM -> medium.setSelected(true);
            case HARD -> hard.setSelected(true);
        }

        infiniteMode.setSelected(OptionModel.getInfinite());
    }

    /**
     * Method bound to the easy JavaFX Button of the view.
     * Will set the corresponding value on the option scene model.
     */
    @FXML
    public void clickOnEasy() {
        OptionModel.setDifficulty(Difficulty.EASY);
    }

    /**
     * Method bound to the medium JavaFX Button of the view.
     * Will set the corresponding value on the option scene model.
     */
    @FXML
    public void clickOnMedium() {
        OptionModel.setDifficulty(Difficulty.MEDIUM);
    }

    /**
     * Method bound to the hard JavaFX Button of the view.
     * Will set the corresponding value on the option scene model.
     */
    @FXML
    public void clickOnHard() {
        OptionModel.setDifficulty(Difficulty.HARD);
    }

    /**
     * Method bound to the difficulty JavaFX checkbox of the view.
     * Will set the corresponding value on the option scene model.
     */
    @FXML
    public void clickOnInfinite() {
        OptionModel.setInfinite(infiniteMode.isSelected());
    }

    /**
     * Method bound to the quit JavaFX Button of the view.
     * Will make the application go back to the title screen scene.
     *
     * @throws IOException the io exception
     */
    @FXML
    public void quit() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TitleScreen.class.getResource("title-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        TitleScreen.getStage().setScene(scene);
        TitleScreen.getStage().show();
    }


}

package com.example.froggeroop.models;

import com.example.froggeroop.util.Difficulty;

/**
 * OptionModel is a model-type module that contains the attributes
 * related to the Option Controller. Works alongside OptionController class
 * and options.fxml file as part of Model View Controller architecture.
 */
public final class OptionModel {
    private static Difficulty difficulty = Difficulty.MEDIUM;
    private static Boolean infinite = false;

    /**
     * get the difficulty of the game
     *
     * @return enum -type Difficulty attribute from EASY to HARD
     */
    public static Difficulty getDifficulty() {
        return difficulty;
    }


    /**
     * set the difficulty of the game
     *
     * @param difficulty enum-type Difficulty attribute from EASY to HARD from
     */
    public static void setDifficulty(Difficulty difficulty) {
        OptionModel.difficulty = difficulty;
    }


    /**
     * get value of infinite mode
     *
     * @return a boolean characterizing the activation of the infinite mode
     */
    public static Boolean getInfinite() {
        return infinite;
    }

    /**
     * set value of infinite mode
     *
     * @param infinite a boolean characterizing the activation of the infinite mode
     */
    public static void setInfinite(Boolean infinite) {
        OptionModel.infinite = infinite;
    }
}

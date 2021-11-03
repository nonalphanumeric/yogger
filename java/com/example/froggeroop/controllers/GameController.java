package com.example.froggeroop.controllers;

import com.example.froggeroop.TitleScreen;
import com.example.froggeroop.entities.*;
import com.example.froggeroop.graphical.*;
import com.example.froggeroop.models.GameModel;
import com.example.froggeroop.models.OptionModel;
import com.example.froggeroop.terrain.DrivableLane;
import com.example.froggeroop.terrain.Sidewalk;
import com.example.froggeroop.terrain.WaterLane;
import com.example.froggeroop.util.Direction;
import com.example.froggeroop.util.Observator;
import com.example.froggeroop.util.Timer;
import com.example.froggeroop.util.exceptions.DeathException;
import com.example.froggeroop.util.exceptions.VictoryException;
import com.example.froggeroop.util.exceptions.YoggerException;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Controller-type module managing the game scene.
 * Also implements some elements of the view-type module not managed by the FXML
 * i.e. the grid object on which the games take place.
 */
public class GameController implements Observator {
    @FXML
    private GridPane gridBase;
    @FXML
    private Label scoreLabel;

    private GridPane grid;
    /**
     * The constant game contains the model-type module of the game scene.
     */
    public static GameModel game;
    private Timer timer;
    private Timer timerForLanes;

    private PrintWriter logPrinter;


    /**
     * Method allowing to get back to the title screen scene, used after the game is over.
     *
     * @throws IOException if error on the path of the fxml file
     */
    private void backToTitle() throws IOException {

        this.logPrinter.close();
        FXMLLoader fxmlLoader = new FXMLLoader(TitleScreen.class.getResource("title-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        TitleScreen.getStage().setScene(scene);
        TitleScreen.getStage().show();
    }

    /**
     * Method called once after constructor, initialize important attributes of the controller
     *
     * @throws IOException the io exception
     */
    @FXML
    public void initialize() throws IOException {

        this.logPrinter = new PrintWriter("log.txt", StandardCharsets.UTF_8);
        logPrinter.println("NEW GAME: ");
        String inf = OptionModel.getInfinite() ? "INFINITE" : "CLASSIC";
        logPrinter.println("DIFFICULTY: " + OptionModel.getDifficulty().toString());
        logPrinter.println("MODE:" + inf);

        this.grid = new GridPane();
        this.grid.setAlignment(Pos.CENTER);
        GridPane.setRowIndex(grid, 0);
        GridPane.setColumnIndex(grid, 0);
        this.gridBase.getChildren().add(grid);

        this.timer = new Timer(0.02);
        this.timerForLanes = new Timer(0.10);

        timer.add(this);

        game = new GameModel();
        game.addToTimer(timerForLanes);

        drawGrid();


    }


    /**
     * Implementation of observator's receiveNotif method.
     * Characterize what the controller will do as it receive signals from
     * the observable i.e. the timer.
     */
    @Override
    public void receiveNotif() {
        game.manageScore(timer.getLapsCounter());

        grid.getScene().setOnKeyPressed(new HandlerKeyboard());

        scoreLabel.setText(Integer.toString(game.getScore()));

        /*Managing scrolling of infinite game*/
        if (OptionModel.getInfinite()) {
            game.scroll(timerForLanes);
        }


        try {
            game.getCor().detect(game);
        } catch (DeathException d) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin de partie");
            alert.setHeaderText("Yogger est mort");
            String s = "";
            if (OptionModel.getInfinite())
                s = "Score:  " + game.getScore();
            alert.setContentText(s);
            alert.setOnHidden(evt ->
            {
                try {
                    backToTitle();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            logPrinter.println("DEFEAT, SCORE: " + game.getScore());
            alert.show();

            this.timerForLanes.setSuspended(true);
            this.timer.setSuspended(true);
        } catch (VictoryException v) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin de partie");
            alert.setHeaderText("Victoire !");
            String s;
            s = "Score:  " + game.getScore();
            alert.setContentText(s);
            alert.setOnHidden(evt ->
            {
                try {
                    backToTitle();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            logPrinter.println("VICTORY, SCORE: " + game.getScore());

            alert.show();

            this.timerForLanes.setSuspended(true);
            this.timer.setSuspended(true);
        } catch (YoggerException f) {
            f.printStackTrace();
            System.out.println("Unhandled frogger exception");
        }

        drawGrid();

    }

    /**
     * Inner class of controller to manage keyboard's input.
     * This will reinitialize at each signal from the timer so that movement
     * will only be done once per signal.
     */
    private final class HandlerKeyboard implements EventHandler<KeyEvent> {
        private boolean done = false;

        public void handle(KeyEvent ke) {
            if (!done) {
                switch (ke.getCode()) {
                    case UP -> game.moveYog(Direction.UP);
                    case LEFT -> game.moveYog(Direction.LEFT);
                    case RIGHT -> game.moveYog(Direction.RIGHT);
                    case DOWN -> game.moveYog(Direction.DOWN);
                    default -> {

                    }
                }
            }
            done = true;

        }
    }

    /**
     * Method to draw the grid on which the game's take place.
     */
    private void drawGrid() {
        this.grid.getChildren().clear();
        for (int row = 0; row < game.getRoad().getVisibleLanes().size(); row++) {
            for (int col = 0; col < 16; col++) {
                StackPane stck = new StackPane();

                ImageView img = new ImageView();

                ArrayList<Entity> entities;

                if (game.getRoad().getVisibleLanes().get(row) instanceof Sidewalk)
                    img.setImage(SidewalkGr.getImage());
                else if (game.getRoad().getVisibleLanes().get(row) instanceof DrivableLane)
                    img.setImage(RoadGr.getImage());
                else if (game.getRoad().getVisibleLanes().get(row) instanceof WaterLane) {
                    img.setImage(WaterGr.getImage());

                }
                stck.getChildren().add(img);

                entities = game.getRoad().getVisibleLanes().get(row).getCells().get(col).getOnCell();


                for (Entity e : entities) {
                    ImageView img2 = new ImageView();
                    if (e instanceof CarPart) {
                        img2.setImage(CarGr.getImage());
                    }
                    if (e instanceof LogPart) {
                        img2.setImage(LogGr.getImage());
                    }
                    if (e instanceof Yog) {
                        img2.setImage(YetiGr.getImage());
                    }
                    if (e instanceof Coin) {
                        img2.setImage(CoinGr.getImage());
                    }
                    if (e instanceof Mine) {
                        img2.setImage(MineGr.getImage());
                    }
                    stck.getChildren().add(img2);
                }

                GridPane.setRowIndex(stck, row);
                GridPane.setColumnIndex(stck, col);
                this.grid.getChildren().addAll(stck);

            }
        }
    }

}

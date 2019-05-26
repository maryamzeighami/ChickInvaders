package GUI.gameplay;

import Controller.Constants;
import enums.BeamLevel;
import enums.KeyState;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import models.AttackSystem;
import models.EnemySystem;
import models.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class GameSceneBuilder {
    private Scene scene;
    private Timeline timeline;
    private javafx.scene.control.TableView<Player> table;

    // key states
    KeyState spaceKey = KeyState.NOT_PRESSED;
    KeyState escapeKey = KeyState.NOT_PRESSED;

    // AttackSystem
    AttackSystem attackSystem = new AttackSystem();
    EnemySystem enemySystem = new EnemySystem();

    public GameSceneBuilder builder(Player player) {



        enemySystem.add(35,1);





        // --- set pictures ---

        // background
        String addr = System.getProperty("user.dir") + "/src/pics/back3.png";
        File file = new File(addr);
        Image background = new Image(file.toURI().toString());

        // space ship pic
        String addrs = System.getProperty("user.dir") + "/src/pics/spaceship.png";
        File file2 = new File(addrs);
        Image spaceShipImage = new Image(file2.toURI().toString());
        ImageView spaceShip = new ImageView(spaceShipImage);
        spaceShip.setFitHeight(130);
        spaceShip.setFitWidth(130);


        // heart pic
        String addrss = System.getProperty("user.dir") + "/src/pics/heart.png";
        File file3 = new File(addrss);
        Image heart1 = new Image(file3.toURI().toString());
        ImageView heart = new ImageView(heart1);
        heart.setFitHeight(40);
        heart.setFitWidth(40);

        // mega seed pic
        String miniMegaSeed = System.getProperty("user.dir") + "/src/pics/seed2.png";
        File miniFile = new File(miniMegaSeed);
        Image mega1 = new Image(miniFile.toURI().toString());
        ImageView megaSeed = new ImageView(mega1);
        megaSeed.setFitHeight(40);
        megaSeed.setFitWidth(40);

        // green pic!
        String gAdres = System.getProperty("user.dir") + "/src/pics/green2.gif";
        File greenFile = new File(gAdres);
        Image greenImage = new Image(greenFile.toURI().toString());
        ImageView green = new ImageView(greenImage);
        green.setFitHeight(50);
        green.setFitWidth(50);

        // --- set info (health, score, ...) ---
        // heart VBox (health infoVBox actually!)
        HBox heartBox = new HBox();
        heartBox.setSpacing(5);
        Text text = new Text();
        text.setText("0");
        heartBox.getChildren().addAll(heart, text);

        // mega VBox
        HBox megaBox = new HBox();
        megaBox.setSpacing(5);
        Text text1 = new Text();
        text1.setText("0");
        megaBox.getChildren().addAll(megaSeed, text1);

        // green VBox
        HBox greenBox = new HBox();
        greenBox.setSpacing(5);
        Text text2 = new Text();
        text2.setText("0");
        greenBox.getChildren().addAll(green, text2);

        // main VBox containing all info
        VBox mainVBox = new VBox();
        mainVBox.setSpacing(5);
        mainVBox.setAlignment(Pos.BOTTOM_LEFT);
        mainVBox.getChildren().addAll(heartBox, megaBox, greenBox);

        // space ship VBox (space ship and every thing attached to it is in this part)
        VBox infoVBox = new VBox();
        infoVBox.setAlignment(Pos.BOTTOM_CENTER);
        infoVBox.getChildren().addAll(spaceShip);

        // --- panes ---
        // main stack pain
        StackPane stackPane = new StackPane();

        stackPane.getChildren().add(new ImageView(background));
        stackPane.getChildren().addAll(infoVBox, mainVBox);
        stackPane.getChildren().addAll(enemySystem.add(45,1));
        scene = new Scene(stackPane, Constants.GAME_SCENE_WIDTH, Constants.GAME_SCENE_HEIGHT);

        // TODO: 4/20/2019 replace with actual fire system
        // beam!
        ArrayList<Beam> beams = new ArrayList<>();

        // --- key and mouse handlers! ---
        // keyboard handler - setOnKeyPressed
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case SPACE:
                    spaceKey = KeyState.PRESSED;
                    break;
                case ESCAPE:
                    // TODO: 4/21/2019 after pressing scape, the game should be paused!
                    ExitMenuStage exitMenuStage = new ExitMenuStage();
                    exitMenuStage.Display();
                    break;

                case DIGIT1:
                    attackSystem.setLevel(BeamLevel.LEVEL_1);
                    break;
                case DIGIT2:
                    attackSystem.setLevel(BeamLevel.LEVEL_2);
                    break;
                case DIGIT3:
                    attackSystem.setLevel(BeamLevel.LEVEL_3);
                    break;
            }
        });

        // keyboard handler - setOnKeyReleased
        scene.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case SPACE:
                    spaceKey = KeyState.NOT_PRESSED;
                    break;
            }
        });

        // keyboard handler - action (does something if key is pressed!) - timeLines and keyFrames
        //// keyFrames
        KeyFrame mainKeyFrame = new KeyFrame(Duration.millis(25), event -> {

            // space key for shooting beams! and handling temperature
            attackSystem.decreaseTemp();
            if (spaceKey == KeyState.PRESSED) {
                Beam[] temp = attackSystem.getBeams(spaceShip);
                if (temp != null) {
                    attackSystem.increaseTemp();
                    beams.addAll(Arrays.asList(temp));
                    stackPane.getChildren().addAll(temp);
                }
            }

            // moving beams forward!
            for (Beam beam : beams) {
                if (beam.getLayoutY() < 0) {
                    beams.remove(beam);
                    stackPane.getChildren().remove(beam);
                } else {
                    beam.setTranslateY(beam.getTranslateY() - beam.getHeight());
                }
            }


        });


        //// timeline
        timeline = new Timeline();
        timeline.getKeyFrames().addAll(mainKeyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.playFromStart();


        // mouse handler
        scene.setOnMouseMoved(event -> {
            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.millis(5));
            transition.setToX(event.getSceneX() - spaceShip.getLayoutX() - 65);
            transition.setToY(event.getSceneY() - spaceShip.getLayoutY() - 65);
            transition.setNode(spaceShip);
            transition.playFromStart();
        });


        return this;
    }


    public Scene getScene() {
        return scene;
    }
}


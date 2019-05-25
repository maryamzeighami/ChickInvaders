package GUI.menues;

import Controller.IO;
import GUI.MainStageHolder;
import GUI.gameplay.GameSceneBuilder;
import GUI.menues.choosePlayerScene.ChoosePlayerSceneBuilder;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import models.DataBase;
import models.Player;

import java.io.File;


public class MainMenuSceneBuilder {
    private Scene scene;
    private Button startBut;
    private Button resumeBut;
    private Button settingsBut;
    private Button exitBut;
    private Button ratBut;
    private Button changbut;


    public MainMenuSceneBuilder build(Player player) {

        //buttons
        resumeBut = new Button("resume");
        startBut = new Button("start");
        settingsBut = new Button("setting");
        exitBut = new Button("exit");
        ratBut = new Button("rating");
        changbut = new Button("change player");

        //change Button
        changbut.setOnAction(e -> MainStageHolder.stage.setScene(new ChoosePlayerSceneBuilder().
                build(DataBase.getInstance().getPlayers()).getScene()));


        // exit button
        exitBut.setOnAction(event -> {
            IO.saveGame();
            System.exit(0);
        });

        //start
        startBut.setOnAction(event -> {
            Scene scene = new GameSceneBuilder().builder(player).getScene();
            MainStageHolder.stage.setScene(scene);
        });


        VBox vbox = new VBox(6);
        vbox.getChildren().addAll(changbut, resumeBut, startBut, ratBut, settingsBut, exitBut);


        //background
        String addr = System.getProperty("user.dir") + "/src/pics/back2.jpg";
        File file = new File(addr);
        Image image = new Image(file.toURI().toString());
        StackPane pane = new StackPane();
        pane.getChildren().add(new ImageView(image));
        vbox.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(vbox);
        scene = new Scene(pane, 1070, 750);

        scene.getStylesheets().add("/GUI/CSS/MainMenuStyle.css");


        return this;


    }


    public Scene getScene() {
        return scene;
    }
}

package models;

import enums.ChickenLevel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


public class Chicken extends ImageView {
//     protected ImageView image;
//
//    private ChickenLevel chickenLevel;
//
//
//    public ImageView getImage(){
//        return this.image;
//    }

    Chicken(Image image){
        super(image);
        this.setFitHeight(60);
        this.setFitWidth(100);
    }




}

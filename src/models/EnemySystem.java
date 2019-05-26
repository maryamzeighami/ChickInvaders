package models;


import Controller.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


public class EnemySystem {

    int number;
    int colum;


    Chicken[] chickens = new Chicken1[number];

    public ImageView[] add(int numbers, int level) {
        this.number=numbers;

        if (number>=40){
            colum=9;
        } else if (number>30){
            colum=8;
        } else {
            colum= 7;
        }



        switch (level) {
            case 1:
                for (int i = 0; i < numbers; i++) {
                    chickens[i] = new Chicken1();

                }
                for (int i = 0; i < numbers; i++) {

                    chickens[i].setTranslateX((i % colum - 4) * Constants.CHICKEN_SIZE + Constants.GAME_SCENE_WIDTH / 4);
                    chickens[i].setTranslateY((i / colum) * Constants.CHICKEN_SIZE + 30);

                }
            case 2:

                for (int i = 0; i < numbers; i++) {
                    if (i< numbers/2) {
                        chickens[i] = new Chicken1();
                    } else {
                        chickens[i]= new Chicken2();

                    }


                }
                for (int i = 0; i < numbers; i++) {

                    chickens[i].setTranslateX((i % colum - 4) * Constants.CHICKEN_SIZE + Constants.GAME_SCENE_WIDTH / 4);
                    chickens[i].setTranslateY((i / colum) * Constants.CHICKEN_SIZE + 30);

                }
            case 3:
                for (int i = 0; i < numbers; i++) {
                    if (i<numbers/3) {
                        chickens[i] = new Chicken1();

                    } else if (i<(numbers/3)*2){
                        chickens[i] = new Chicken2();

                    } else {
                        chickens[i] = new Chicken3();

                    }


                }
                for (int i = 0; i < numbers; i++) {

                    chickens[i].setTranslateX((i % colum - 4) * Constants.CHICKEN_SIZE + Constants.GAME_SCENE_WIDTH / 4);
                    chickens[i].setTranslateY((i / colum) * Constants.CHICKEN_SIZE + 30);

                }
            case 4 :
                for (int i = 0; i < numbers; i++) {
                    if (i<numbers/4) {
                        chickens[i] = new Chicken1();

                    } else if (i<(numbers/4)*2){
                        chickens[i] = new Chicken2();

                    } else if (i<(numbers/4)*3){
                        chickens[i] = new Chicken3();

                    } else {
                        chickens[i] = new Chicken4();

                    }



                }
                for (int i = 0; i < numbers; i++) {

                    chickens[i].setTranslateX((i % colum - 4) * Constants.CHICKEN_SIZE + Constants.GAME_SCENE_WIDTH / 4);
                    chickens[i].setTranslateY((i / colum) * Constants.CHICKEN_SIZE + 30);

                }
        }

        return chickens;

    }


}

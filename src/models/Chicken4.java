package models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Chicken4 extends Chicken {

    int health=4;
    static Image[] image= new Image[2];
    static {
    image[0] = new Image(new File(System.getProperty("user.dir") + "/src/pics/head1.png").toURI().toString());
    image[1] = new Image(new File(System.getProperty("user.dir") + "/src/pics/head2.png").toURI().toString());

    }
    Chicken4(){

        super(image[0]);



    }


    public void blink() {
//        this.image = new ImageView(new Image(new File(System.getProperty("user.dir") + "/src/pics/head2.png").toURI().toString()));
        setImage(new Image(new File(System.getProperty("user.dir") + "/src/pics/head2.png").toURI().toString()));

    }

}

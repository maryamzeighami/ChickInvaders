package GUI.gameplay;

import javafx.scene.shape.Rectangle;

public class Beam extends Rectangle {
    public int dx;
    public int dy;
    public int firePower;

    public Beam(double width, double height) {
        super(width, height);
    }
}

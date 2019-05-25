package models;

import java.io.Serializable;

public class Game implements Serializable {
    private SpaceShip spaceShip;
    private Player player;
    private int level;
    private java.util.ArrayList<Chicken> chickens;
}

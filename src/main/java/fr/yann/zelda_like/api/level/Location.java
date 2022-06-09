package fr.yann.zelda_like.api.level;

public interface Location {

    int getX();

    int getY();

    Orientation getOrientation();

    enum Orientation {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
}

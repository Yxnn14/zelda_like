package fr.yann.zelda_like.api.level;

public interface Location {

    int getX();

    Location addX(int x);
    Location removeX(int x);

    int getY();

    Location addY(int y);
    Location removeY(int y);
    Location add(int x, int y);
    Location remove(int x, int y);

    Orientation getOrientation();

    void setOrientation(Orientation orientation);

    double getDistance(Location location);

    Location clone();

    enum Orientation {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
}

package fr.yann.zelda_like.core.level;

import fr.yann.zelda_like.api.level.Location;

public class ImplLocation implements Location {

    public static Location create(int x, int y) {
        return ImplLocation.create(x, y, Orientation.NORTH);
    }

    public static Location create(int x, int y, Orientation orientation) {
        return new ImplLocation(x, y, orientation);
    }

    private final Orientation orientation;
    private final int x;
    private final int y;

    private ImplLocation(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public Orientation getOrientation() {
        return this.orientation;
    }
}

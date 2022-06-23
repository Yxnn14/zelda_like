package fr.yann.zelda_like.core.level;

import fr.yann.zelda_like.api.level.Location;

public class ImplLocation implements Location {

    public static Location create(int x, int y) {
        return ImplLocation.create(x, y, Orientation.NORTH);
    }

    public static Location create(int x, int y, Orientation orientation) {
        return new ImplLocation(x, y, orientation);
    }

    private final int x;
    private final int y;
    private Orientation orientation;

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
    public Location addX(int x) {
        return ImplLocation.create(this.x + x, this.y, this.orientation);
    }

    @Override
    public Location removeX(int x) {
        return ImplLocation.create(this.x - x, this.y, this.orientation);
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public Location addY(int y) {
        return ImplLocation.create(this.x, this.y + y, this.orientation);
    }

    @Override
    public Location removeY(int y) {
        return ImplLocation.create(this.x, this.y - y, this.orientation);
    }

    @Override
    public Location add(int x, int y) {
        return ImplLocation.create(this.x + x, this.y + y, this.orientation);
    }

    @Override
    public Location remove(int x, int y) {
        return ImplLocation.create(this.x - x, this.y - y, this.orientation);
    }

    @Override
    public Orientation getOrientation() {
        return this.orientation;
    }

    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public double getDistance(Location location) {
        // TODO: x = sqrt(x² + y²)
        int x = Math.abs(this.x - location.getX());
        int y = Math.abs(this.y - location.getY());
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public Location clone() {
        return new ImplLocation(this.x, this.y, this.orientation);
    }
}

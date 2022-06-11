package fr.yann.zelda_like.core.particule;

import fr.yann.zelda_like.api.particule.Vector;

public class ImplVector implements Vector {
    public static Vector create(float x, float y) {
        return new ImplVector(x, y);
    }

    private float x;
    private float y;

    private ImplVector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX() {
        return this.x;
    }

    @Override
    public float getY() {
        return this.y;
    }

    @Override
    public void apply(Vector vector) {
        this.x += vector.getX();
        this.y += vector.getY();
    }

    @Override
    public void multiply(float multiply) {
        this.x *= multiply;
        this.y *= multiply;
    }
}

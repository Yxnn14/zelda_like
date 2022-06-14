package fr.yann.zelda_like.api.particule;

public interface Vector {
    float getX();

    float getY();

    void apply(Vector vector);

    void multiply(float multiply);
}

package fr.yann.zelda_like.api.particule;

import fr.yann.zelda_like.api.updater.UpdaterManager;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface Particule {

    Vector getLocation();

    Vector getVelocity();

    void setVelocity(Vector velocity);

    int getLifetime();

    Image getTexture();

    Color getColor();

    UpdaterManager<Particule> getUpdaterManager();
}

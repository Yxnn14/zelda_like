package fr.yann.zelda_like.api.entity;

import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.api.updater.UpdaterManager;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface Entity {

    String getName();

    Location getLocation();

    void setLocation(Location location);

    Color getColor();

    Image getTexture();

    int getHealth();

    UpdaterManager<Entity> getUpdaterManager();

    boolean interact(Entity entity);
}

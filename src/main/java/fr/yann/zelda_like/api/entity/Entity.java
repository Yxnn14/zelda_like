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

    void addHealth(int health);

    void removeHealth(int health);

    int getDamage();

    UpdaterManager<Entity> getUpdaterManager();

    boolean interact(Entity entity);

    boolean isInvulnerable();

    boolean isDeath();

    boolean isVisible();

    void setVisible(boolean visible);
}

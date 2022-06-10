package fr.yann.zelda_like.api.entity;

import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.api.updater.UpdaterManager;
import javafx.scene.paint.Color;

public interface Entity {

    String getName();

    Location getLocation();

    void setLocation(Location location);

    /**
     * @deprecated
     */
    Color getColor();

    int getHealth();


    UpdaterManager<Entity> getUpdaterManager();
}

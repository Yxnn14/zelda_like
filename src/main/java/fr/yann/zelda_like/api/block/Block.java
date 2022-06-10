package fr.yann.zelda_like.api.block;

import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.api.updater.UpdaterManager;
import javafx.scene.paint.Color;

public interface Block {
    Location getLocation();

    boolean isTransparent();

    /**
     * @deprecated
     */
    Color getColor();

    UpdaterManager<Block> getUpdaterManager();
}

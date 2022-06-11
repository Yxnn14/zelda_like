package fr.yann.zelda_like.api.block;

import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.api.updater.UpdaterManager;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface Block {
    Location getLocation();

    boolean isTransparent();

    Color getColor();

    Image getTexture();

    UpdaterManager<Block> getUpdaterManager();

    boolean interact(Entity entity);
}

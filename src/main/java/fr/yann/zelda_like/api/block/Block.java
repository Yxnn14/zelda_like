package fr.yann.zelda_like.api.block;

import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public interface Block {
    Location getLocation();

    /**
     * @deprecated
     */
    Color getColor();
}

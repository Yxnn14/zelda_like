package fr.yann.zelda_like.api.inventory;

import fr.yann.zelda_like.api.entity.Entity;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface Item {

    String getName();

    boolean use(Entity entity);

    Color getColor();

    Image getTexture();
}

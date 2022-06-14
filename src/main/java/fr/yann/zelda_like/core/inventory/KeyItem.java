package fr.yann.zelda_like.core.inventory;

import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.inventory.Item;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class KeyItem extends AbstractItem {
    private static final Image TEXTURE = new Image("assets/textures/key.png");
    public KeyItem() {
        super("Clef", Color.color(0.2, 0.5, 0.8));
    }

    @Override
    public Image getTexture() {
        return KeyItem.TEXTURE;
    }

    @Override
    public boolean use(Entity entity) {
        return false;
    }
}

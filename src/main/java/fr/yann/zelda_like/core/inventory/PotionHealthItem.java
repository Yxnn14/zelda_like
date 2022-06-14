package fr.yann.zelda_like.core.inventory;

import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.inventory.Item;
import fr.yann.zelda_like.core.entity.ImplPlayerEntity;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PotionHealthItem extends AbstractItem {
    private static final Image TEXTURE = new Image("assets/textures/potion_health.png");
    public PotionHealthItem() {
        super("Potion de soins", Color.color(0.2, 0.5, 0.8));
    }
    @Override
    public Image getTexture() {
        return PotionHealthItem.TEXTURE;
    }

    @Override
    public boolean use(Entity entity) {
        entity.addHealth(5);
        return true;
    }
}


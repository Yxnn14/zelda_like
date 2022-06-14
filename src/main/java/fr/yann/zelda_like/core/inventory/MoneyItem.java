package fr.yann.zelda_like.core.inventory;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class MoneyItem extends AbstractItem {

    public static final Image TEXTURE = new Image("assets/textures/money.png");

    public MoneyItem() {
        super("Money", Color.color(.7, .6, 0));
    }

    @Override
    public Image getTexture() {
        return MoneyItem.TEXTURE;
    }
}

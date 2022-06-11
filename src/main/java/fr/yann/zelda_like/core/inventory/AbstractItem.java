package fr.yann.zelda_like.core.inventory;

import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.inventory.Item;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class AbstractItem implements Item {

    protected final String name;
    protected final Color color;

    public AbstractItem(String name, Color color) {
        this.name = name;
        this.color = color;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean use(Entity entity) {
        return true;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public Image getTexture() {
        return null;
    }

    @Override
    public Image getTexture(Location.Orientation orientation) {
        return this.getTexture();
    }
}

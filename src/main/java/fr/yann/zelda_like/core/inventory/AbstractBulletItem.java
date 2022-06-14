package fr.yann.zelda_like.core.inventory;

import fr.yann.zelda_like.api.inventory.BulletItem;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class AbstractBulletItem extends AbstractItem implements BulletItem {

    protected final int range;
    protected final int damage;
    protected final int speed;

    public AbstractBulletItem(String name, Color color, int range, int damage, int speed) {
        super(name, color);
        this.range = range;
        this.damage = damage;
        this.speed = speed;
    }

    @Override
    public int getRange() {
        return this.range;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }
}

package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.ItemEntity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.inventory.Item;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ImplItemEntity extends AbstractEntity implements ItemEntity {

    protected Item item;

    public ImplItemEntity(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, "item Not Found", location, Color.color(0, 0, 0), 1);
        super.invulnerable = true;
    }

    @Override
    public String getName() {
        return this.item != null ? this.item.getName() : super.getName();
    }

    @Override
    public Color getColor() {
        return this.item != null ? this.item.getColor() : super.getColor();
    }

    @Override
    public Item getItem() {
        return this.item;
    }

    @Override
    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean canPickup() {
        return true;
    }

    @Override
    public boolean interact(Entity entity) {
        if (entity instanceof PlayerEntity && this.canPickup()) {
            ((PlayerEntity) entity).getInventory().addItem(this.getItem());
            this.zeldaLike.getLevelManager().get().removeEntity(this);
            return true;
        }
        return super.interact(entity);
    }

    @Override
    public Image getTexture() {
        return this.item != null ? this.item.getTexture(this.getLocation().getOrientation()) : super.getTexture();
    }
}

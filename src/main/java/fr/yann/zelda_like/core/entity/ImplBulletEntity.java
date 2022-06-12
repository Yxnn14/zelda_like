package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.BulletEntity;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.inventory.BulletItem;
import fr.yann.zelda_like.api.inventory.Item;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.updater.entity.BulletUpdater;

public class ImplBulletEntity extends ImplItemEntity implements BulletEntity {

    private Entity shooter;
    public ImplBulletEntity(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, location);
    }

    @Override
    public void setItem(Item item) {
        if (!(item instanceof BulletItem)) {
            return;
        }
        this.getUpdaterManager()
                .remove(BulletUpdater.class)
                .add(new BulletUpdater(((BulletItem) item).getSpeed()));
        super.setItem(item);
    }

    @Override
    public boolean canPickup() {
        return false;
    }

    @Override
    public boolean interact(Entity entity) {
        return false;
    }

    @Override
    public int getDamage() {
        return this.item instanceof BulletItem ? ((BulletItem) this.item).getDamage() : super.getDamage();
    }

    @Override
    public Entity getShooter() {
        return this.shooter;
    }

    @Override
    public void setShooter(Entity entity) {
        this.shooter = entity;
    }
}

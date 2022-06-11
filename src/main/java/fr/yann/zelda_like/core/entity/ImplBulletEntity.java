package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.BulletEntity;
import fr.yann.zelda_like.api.inventory.BulletItem;
import fr.yann.zelda_like.api.inventory.Item;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.updater.entity.BulletUpdater;

public class ImplBulletEntity extends ImplItemEntity implements BulletEntity {
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
}

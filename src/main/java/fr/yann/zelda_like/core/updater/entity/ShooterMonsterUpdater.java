package fr.yann.zelda_like.core.updater.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.ShooterEntity;
import fr.yann.zelda_like.core.inventory.ArrowItem;
import fr.yann.zelda_like.core.updater.AbstractUpdater;

public class ShooterMonsterUpdater extends AbstractUpdater<Entity> {
    public ShooterMonsterUpdater(int tickUpdater) {
        super(tickUpdater);
    }

    @Override
    public void onUpdate(ZeldaLike zeldaLike, Entity entity) {
        if (!(entity instanceof ShooterEntity)) {
            return;
        }
        ((ShooterEntity) entity).shoot(new ArrowItem());
    }
}

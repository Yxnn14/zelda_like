package fr.yann.zelda_like.core.updater.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.BulletEntity;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.inventory.BulletItem;
import fr.yann.zelda_like.api.inventory.Item;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.core.updater.AbstractUpdater;

public class BulletUpdater extends AbstractUpdater<Entity> {

    private int range;

    public BulletUpdater(int tickUpdater) {
        super(tickUpdater);
    }

    @Override
    public void onUpdate(ZeldaLike zeldaLike, Entity entity) {
        if (!(entity instanceof BulletEntity)) {
            return;
        }

        final Item item = ((BulletEntity) entity).getItem();

        if (!(item instanceof BulletItem)) {
            return;
        }

        final Level level = zeldaLike.getLevelManager().get();
        this.range++;

        int x = 0;
        int y = 0;
        switch (entity.getLocation().getOrientation()) {
            case EAST -> x = 1;
            case WEST -> x = -1;
            case NORTH -> y = -1;
            case SOUTH -> y = 1;
        }

        if (this.range >= ((BulletItem) item).getRange()) {
            level.removeEntity(entity);
            return;
        }
        if (!level.getBlockAt(entity.getLocation().getX() + x, entity.getLocation().getY() + y).isTransparent()) {
            level.removeEntity(entity);
            return;
        }
        if (level.getEntityAt(entity.getLocation().getX() + x, entity.getLocation().getY() + y) != null) {
            level.removeEntity(entity);
            return;
        }
        level.moveEntity(entity, entity.getLocation().add(x, y));
    }
}

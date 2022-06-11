package fr.yann.zelda_like.core.updater.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.controller.Controller;
import fr.yann.zelda_like.api.entity.BulletEntity;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.api.updater.Updater;
import fr.yann.zelda_like.core.entity.ImplBulletEntity;
import fr.yann.zelda_like.core.inventory.DemoBulletItem;

public class PlayerActionUpdater implements Updater<Entity> {

    @Override
    public void update(ZeldaLike zeldaLike, Entity entity) {

        if (!(entity instanceof PlayerEntity)) {
            return;
        }
        final Controller controller = zeldaLike.getControllerManager().of(Controller.ACTION);
        if (controller == null || !controller.isPressed()) {
            return;
        }

        final Level level = zeldaLike.getLevelManager().get();

        int x = 0;
        int y = 0;

        switch (entity.getLocation().getOrientation()) {
            case EAST -> x = 1;
            case WEST -> x = -1;
            case NORTH -> y = -1;
            case SOUTH -> y = 1;
        }

        final Location location = entity.getLocation().add(x, y);
        final Entity targetEntity = level.getEntityAt(location);
        if (targetEntity != null && targetEntity.interact(entity)) {
            return;
        }
        final Block block = level.getBlockAt(location);
        if (!block.interact(entity) && block.isTransparent() && targetEntity == null) {
            BulletEntity bulletEntity = level.spawn(ImplBulletEntity.class, entity.getLocation().add(x, y));
            bulletEntity.setItem(new DemoBulletItem());
        }

    }
}

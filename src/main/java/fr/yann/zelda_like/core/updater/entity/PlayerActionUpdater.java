package fr.yann.zelda_like.core.updater.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.controller.Controller;
import fr.yann.zelda_like.api.entity.BulletEntity;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.ItemEntity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.inventory.BulletItem;
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

        if (entity.getLocation().getOrientation().equals(Location.Orientation.SOUTH)) {
            y = 1;
        } else if (entity.getLocation().getOrientation().equals(Location.Orientation.NORTH)) {
            y = -1;
        } else if (entity.getLocation().getOrientation().equals(Location.Orientation.WEST)) {
            x = -1;
        } else if (entity.getLocation().getOrientation().equals(Location.Orientation.EAST)) {
            x = 1;
        }
        final Location location = entity.getLocation().add(x, y);
        final Entity targetEntity = level.getEntityAt(location);

        if (targetEntity instanceof ItemEntity itemEntity) {
            if (itemEntity.canPickup()) {
                ((PlayerEntity) entity).getInventory().addItem(itemEntity.getItem());
                level.removeEntity(targetEntity);
            }
            return;
        }
        if (targetEntity == null && level.getBlockAt(location).isTransparent()) {
            BulletEntity bulletEntity = level.spawn(ImplBulletEntity.class, entity.getLocation().add(x, y));
            bulletEntity.setItem(new DemoBulletItem());
        }

    }
}

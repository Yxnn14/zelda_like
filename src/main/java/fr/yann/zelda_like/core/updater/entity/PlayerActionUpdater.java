package fr.yann.zelda_like.core.updater.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.controller.Controller;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.ItemEntity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.api.updater.Updater;

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
        Entity targetEntity = null;


        if (entity.getLocation().getOrientation().equals(Location.Orientation.SOUTH)) {
            targetEntity = level.getEntityAt(entity.getLocation().getX(), entity.getLocation().getY() + 1);
        } else if (entity.getLocation().getOrientation().equals(Location.Orientation.NORTH)) {
            targetEntity = level.getEntityAt(entity.getLocation().getX(), entity.getLocation().getY() - 1);
        } else if (entity.getLocation().getOrientation().equals(Location.Orientation.WEST)) {
            targetEntity = level.getEntityAt(entity.getLocation().getX() - 1, entity.getLocation().getY());
        } else if (entity.getLocation().getOrientation().equals(Location.Orientation.EAST)) {
            targetEntity = level.getEntityAt(entity.getLocation().getX() + 1, entity.getLocation().getY());
        }

        if (targetEntity instanceof ItemEntity itemEntity) {

            ((PlayerEntity) entity).getInventory().addItem(itemEntity.getItem());
            level.removeEntity(targetEntity);

        }
    }
}

package fr.yann.zelda_like.core.updater.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.controller.Controller;
import fr.yann.zelda_like.api.controller.ControllerManager;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.api.updater.Updater;

public class ControllerUpdater implements Updater<Entity> {

    private int sleepTick;
    private boolean mouvement;

    @Override
    public void update(ZeldaLike zeldaLike, Entity entity) {
        final ControllerManager controllerManager = zeldaLike.getControllerManager();
        if (!this.mouvement) {
            Controller controller = controllerManager.of(Controller.UP);
            if (controller != null && controller.isPressed()) {
                if (!entity.getLocation().getOrientation().equals(Location.Orientation.NORTH)) {
                    entity.getLocation().setOrientation(Location.Orientation.NORTH);
                } else {
                    zeldaLike.getLevelManager().get().moveEntity(entity, entity.getLocation().removeY(1));
                }
                this.mouvement = true;
            }

            controller = controllerManager.of(Controller.DOWN);
            if (controller != null && controller.isPressed()) {
                if (!entity.getLocation().getOrientation().equals(Location.Orientation.SOUTH)) {
                    entity.getLocation().setOrientation(Location.Orientation.SOUTH);
                } else {
                    zeldaLike.getLevelManager().get().moveEntity(entity, entity.getLocation().addY(1));
                }
                this.mouvement = true;
            }

            if (!this.mouvement) {
                controller = controllerManager.of(Controller.LEFT);
                if (controller != null && controller.isPressed()) {
                    if (!entity.getLocation().getOrientation().equals(Location.Orientation.WEST)) {
                        entity.getLocation().setOrientation(Location.Orientation.WEST);
                    } else {
                        zeldaLike.getLevelManager().get().moveEntity(entity, entity.getLocation().removeX(1));
                    }
                    this.mouvement = true;
                }

                controller = controllerManager.of(Controller.RIGHT);
                if (controller != null && controller.isPressed()) {
                    if (!entity.getLocation().getOrientation().equals(Location.Orientation.EAST)) {
                        entity.getLocation().setOrientation(Location.Orientation.EAST);
                    } else {
                        zeldaLike.getLevelManager().get().moveEntity(entity, entity.getLocation().addX(1));
                    }
                    this.mouvement = true;
                }
            }
            if (this.mouvement) {
                this.sleepTick = 1;
            }
        } else {
            this.mouvement = (
                controllerManager.of(Controller.UP).isPressed()
                    || controllerManager.of(Controller.DOWN).isPressed()
                    || controllerManager.of(Controller.LEFT).isPressed()
                    || controllerManager.of(Controller.RIGHT).isPressed()
            ) && this.sleepTick > 0;
            this.sleepTick--;
        }
    }
}

package fr.yann.zelda_like.core.updater.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.controller.Controller;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.updater.Updater;

public class InventoryOpeningUpdater implements Updater<Entity> {

    private boolean pressed = false;

    @Override
    public void update(ZeldaLike zeldaLike, Entity entity) {
        if (entity instanceof PlayerEntity player) {
            final Controller controller = zeldaLike.getControllerManager().of(Controller.OPEN_INVENTORY);
            if (controller == null) {
                return;
            }
            if (!this.pressed && controller.isPressed()) {
                if (player.getInventoryView() != null) {
                    player.closeInventory();
                } else {
                    player.openInventory(player.getInventory());
                }
            }
            this.pressed = controller.isPressed();
        }
    }
}

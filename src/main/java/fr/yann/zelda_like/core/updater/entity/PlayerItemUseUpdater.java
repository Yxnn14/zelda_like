package fr.yann.zelda_like.core.updater.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.controller.Controller;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.inventory.Item;
import fr.yann.zelda_like.api.updater.Updater;

public class PlayerItemUseUpdater implements Updater<Entity> {

    @Override
    public void update(ZeldaLike zeldaLike, Entity entity) {
        if (!(entity instanceof PlayerEntity)) {
            return;
        }

        this.update(zeldaLike, (PlayerEntity) entity, Controller.SLOT_1, 0);
        this.update(zeldaLike, (PlayerEntity) entity, Controller.SLOT_2, 1);
        this.update(zeldaLike, (PlayerEntity) entity, Controller.SLOT_3, 2);
        this.update(zeldaLike, (PlayerEntity) entity, Controller.SLOT_4, 3);
        this.update(zeldaLike, (PlayerEntity) entity, Controller.SLOT_5, 4);
        this.update(zeldaLike, (PlayerEntity) entity, Controller.SLOT_6, 5);
        this.update(zeldaLike, (PlayerEntity) entity, Controller.SLOT_7, 6);
        this.update(zeldaLike, (PlayerEntity) entity, Controller.SLOT_8, 7);
        this.update(zeldaLike, (PlayerEntity) entity, Controller.SLOT_9, 8);
        this.update(zeldaLike, (PlayerEntity) entity, Controller.SLOT_10, 9);

    }

    private void update(ZeldaLike zeldaLike, PlayerEntity player, String key, int slot) {
        final Controller controller = zeldaLike.getControllerManager().of(key);
        if (controller == null || !controller.isPressed() || player.getInventory().getSize() <= slot) {
            return;
        }

        final Item item = player.getInventory().getItemAt(slot);
        if (item != null && item.use(player)) {
            player.getInventory().removeItem(slot);
        }
    }

}

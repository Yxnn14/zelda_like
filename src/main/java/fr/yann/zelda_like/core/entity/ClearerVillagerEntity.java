package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.InventoryEntity;
import fr.yann.zelda_like.api.entity.ItemEntity;
import fr.yann.zelda_like.api.inventory.Item;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.level.ImplLocation;
import javafx.scene.paint.Color;

import java.util.Random;

public class ClearerVillagerEntity extends AbstractVillagerEntity {
    public ClearerVillagerEntity(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, "Clearer Villager", location, Color.color(1.0, 1.0, 0.0), 1);
        this.invulnerable = true;
    }

    @Override
    public boolean interact(Entity entity) {
        if (entity instanceof InventoryEntity inventoryEntity) {
            final Level level = this.zeldaLike.getLevelManager().get();
            final Random random = new Random();
            for (int i = 0; i < inventoryEntity.getInventory().getSize(); i++) {
                final Item item = inventoryEntity.getInventory().getItemAt(i);
                if (item == null) {
                    continue;
                }
                Entity target;
                Block block = null;
                int x, y;
                do {
                    x = random.nextInt(level.getWidth());
                    y = random.nextInt(level.getHeight());
                    target = level.getEntityAt(x, y);
                    if (target == null) {
                        block = level.getBlockAt(x, y);
                    }
                } while (target != null || (block != null && !block.isTransparent()));
                inventoryEntity.getInventory().removeItem(i);
                final ItemEntity itemEntity = level.spawn(ImplItemEntity.class, ImplLocation.create(x, y));
                itemEntity.setItem(item);
            }
        }
        return super.interact(entity);
    }
}

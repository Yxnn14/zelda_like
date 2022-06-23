package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.InventoryEntity;
import fr.yann.zelda_like.api.inventory.Item;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public class KeeperVillagerEntity extends AbstractVillagerEntity {
    private Item item;

    public KeeperVillagerEntity(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, "Keeper", location, Color.color(0.0, 1.0, 1.0), 1);
        this.invulnerable = true;
    }

    @Override
    public boolean interact(Entity entity) {
        if (entity instanceof InventoryEntity inventoryEntity) {
            if (this.item != null) {
                inventoryEntity.getInventory().addItem(this.item);
                this.item = null;
                return true;
            }

            for (int i = 0; i < inventoryEntity.getInventory().getSize(); i++) {
                this.item = inventoryEntity.getInventory().getItemAt(i);
                if (this.item != null) {
                    inventoryEntity.getInventory().removeItem(i);
                    break;
                }
            }
        }
        if (this.item == null) {
            return true;
        }
        return super.interact(entity);
    }
}

package fr.yann.zelda_like.api.entity;

import fr.yann.zelda_like.api.inventory.Inventory;

public interface InventoryEntity extends Entity {
    Inventory getInventory();
}

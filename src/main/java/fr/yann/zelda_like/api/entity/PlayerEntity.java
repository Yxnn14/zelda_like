package fr.yann.zelda_like.api.entity;

import fr.yann.zelda_like.api.inventory.Inventory;

public interface PlayerEntity extends MoneyEntity, InventoryEntity {
    Inventory getInventoryView();

    void openInventory(Inventory inventory);

    void closeInventory();
}

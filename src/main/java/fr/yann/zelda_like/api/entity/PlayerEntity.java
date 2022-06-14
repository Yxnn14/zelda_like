package fr.yann.zelda_like.api.entity;

import fr.yann.zelda_like.api.inventory.Inventory;
import fr.yann.zelda_like.api.inventory.Item;

public interface PlayerEntity extends MoneyEntity, InventoryEntity, ShooterEntity {
    Inventory getInventoryView();

    void openInventory(Inventory inventory);

    void closeInventory();

    Item getCursorItem();

    void setCursorItem(Item item);
}

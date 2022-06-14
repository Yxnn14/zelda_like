package fr.yann.zelda_like.api.entity;

import fr.yann.zelda_like.api.inventory.Item;

public interface ItemEntity {

    Item getItem();

    void setItem(Item item);

    boolean canPickup();
}

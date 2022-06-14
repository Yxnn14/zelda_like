package fr.yann.zelda_like.api.entity;

import fr.yann.zelda_like.api.inventory.Item;

public interface MerchantEntity extends VillagerEntity {
    Item getSoldItem();
    void setSoldItem(Item item);
    int getPrice();
    void setPrice(int price);
    int getStock();
    void setStock(int stock);
}

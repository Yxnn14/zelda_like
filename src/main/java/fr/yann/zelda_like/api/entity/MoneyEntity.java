package fr.yann.zelda_like.api.entity;

public interface MoneyEntity extends InventoryEntity {
    int getMoney();

    void addMoney(int money);

    void removeMoney(int money);
}

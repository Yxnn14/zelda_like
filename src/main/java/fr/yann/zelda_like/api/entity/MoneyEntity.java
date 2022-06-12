package fr.yann.zelda_like.api.entity;

public interface MoneyEntity extends Entity {
    int getMoney();

    void addMoney(int money);

    void removeMoney(int money);
}

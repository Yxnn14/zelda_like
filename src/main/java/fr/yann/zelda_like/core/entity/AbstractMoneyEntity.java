package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.MoneyEntity;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public class AbstractMoneyEntity extends AbstractEntity implements MoneyEntity {

    protected int money;

    protected AbstractMoneyEntity (ZeldaLike zeldaLike, String name, Location location, Color color, int health) {
        super(zeldaLike, name, location, color, health);
    }

    @Override
    public int getMoney() {
        return this.money;
    }

    @Override
    public void addMoney(int money) {
        this.money += money;
    }

    @Override
    public void removeMoney(int money) {
        this.money -= money;
    }
}

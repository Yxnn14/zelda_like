package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.MerchantEntity;
import fr.yann.zelda_like.api.entity.MoneyEntity;
import fr.yann.zelda_like.api.inventory.Item;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public abstract class AbstractMerchantEntity extends AbstractVillagerEntity implements MerchantEntity {
    private int price;
    private Item item;
    private int stock;

    protected AbstractMerchantEntity(ZeldaLike zeldaLike, String name, Location location, Color color, int health) {
        super(zeldaLike, name, location, color, health);
    }

    @Override
    public Item getSoldItem() {
        return this.item;
    }

    @Override
    public void setSoldItem(Item item) {
        this.item = item;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public int getStock() {
        return this.stock;
    }

    @Override
    public boolean interact(Entity entity) {
        if (this.stock > 0 && entity instanceof MoneyEntity moneyEntity) {
            if (moneyEntity.getMoney() >= this.getPrice()) {
                moneyEntity.removeMoney(this.getPrice());
                this.stock--;
                moneyEntity.getInventory().addItem(this.getSoldItem());
            }
        }

        return super.interact(entity);
    }
}

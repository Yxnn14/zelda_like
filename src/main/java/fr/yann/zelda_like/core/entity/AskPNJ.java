package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.MoneyEntity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.inventory.KeyItem;
import javafx.scene.paint.Color;

public class AskPNJ extends AbstractMerchantEntity {
    public AskPNJ(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, "Ask", location, Color.color(0.2, 0.5, 0.8), 1);
        this.invulnerable = true;
    }

    @Override
    public boolean interact(Entity entity) {
        if (this.stock > 0 && entity instanceof MoneyEntity moneyEntity) {
            if (moneyEntity.getInventory().contains(KeyItem.class)) {
                if (moneyEntity.getMoney() >= this.getPrice()) {
                    moneyEntity.removeMoney(this.getPrice());
                    this.stock--;
                    moneyEntity.getInventory().addItem(this.getSoldItem());
                }
            }

        }
        return true;
    }
}

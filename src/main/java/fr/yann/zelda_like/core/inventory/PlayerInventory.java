package fr.yann.zelda_like.core.inventory;

import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.inventory.Item;

public class PlayerInventory extends AbstractInventory {

    private final PlayerEntity player;

    public PlayerInventory(PlayerEntity player) {
        super(10);
        this.player = player;
    }

    @Override
    public boolean addItem(Item item) {
        if (item instanceof MoneyItem) {
            this.player.addMoney(1);
            return true;
        }
        return super.addItem(item);
    }

    @Override
    public void addItem(Item item, int slot) {
        if (item instanceof MoneyItem) {
            this.player.addMoney(1);
            return;
        }
        super.addItem(item, slot);
    }
}

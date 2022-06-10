package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.inventory.Inventory;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.inventory.PlayerInventory;
import fr.yann.zelda_like.core.updater.entity.ControllerUpdater;
import fr.yann.zelda_like.core.updater.entity.InventoryOpeningUpdater;
import fr.yann.zelda_like.core.updater.entity.PlayerActionUpdater;
import fr.yann.zelda_like.core.updater.entity.PlayerItemUseUpdater;
import javafx.scene.paint.Color;

public class ImplPlayerEntity extends AbstractMoneyEntity implements PlayerEntity {

    private final Inventory inventory;

    private Inventory inventoryView;

    public ImplPlayerEntity(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, "Player", location, Color.color(0.5, 0.5, 0), 20);
        this.getUpdaterManager()
            .add(new ControllerUpdater())
            .add(new InventoryOpeningUpdater())
            .add(new PlayerActionUpdater())
            .add(new PlayerItemUseUpdater());
        this.inventory = new PlayerInventory();
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public Inventory getInventoryView() {
        return this.inventoryView;
    }

    @Override
    public void openInventory(Inventory inventory) {
        this.inventoryView = inventory;
    }

    @Override
    public void closeInventory() {
        this.inventoryView = null;
    }
}

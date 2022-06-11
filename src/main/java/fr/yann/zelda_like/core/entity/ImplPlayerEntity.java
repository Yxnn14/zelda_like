package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.inventory.Inventory;
import fr.yann.zelda_like.api.inventory.Item;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.block.DemoBlock;
import fr.yann.zelda_like.core.inventory.PlayerInventory;
import fr.yann.zelda_like.core.updater.entity.ControllerUpdater;
import fr.yann.zelda_like.core.updater.entity.InventoryOpeningUpdater;
import fr.yann.zelda_like.core.updater.entity.PlayerActionUpdater;
import fr.yann.zelda_like.core.updater.entity.PlayerItemUseUpdater;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ImplPlayerEntity extends AbstractMoneyEntity implements PlayerEntity {

    private static final Image TEXTURE_SOUTH = new Image("assets/textures/sprite_south.png");
    private static final Image TEXTURE_NORTH = new Image("assets/textures/sprite_north.png");
    private static final Image TEXTURE_WEST = new Image("assets/textures/sprite_west.png");
    private static final Image TEXTURE_EAST = new Image("assets/textures/sprite_east.png");

    private final Inventory inventory;

    private Inventory inventoryView;
    private Item cursorItem;


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

    @Override
    public Item getCursorItem() {
        return this.cursorItem;
    }

    @Override
    public void setCursorItem(Item item) {
        this.cursorItem = item;
    }

    @Override
    public Image getTexture() {
        return switch (this.getLocation().getOrientation()) {
            case SOUTH -> ImplPlayerEntity.TEXTURE_SOUTH;
            case NORTH -> ImplPlayerEntity.TEXTURE_NORTH;
            case WEST -> ImplPlayerEntity.TEXTURE_WEST;
            case EAST -> ImplPlayerEntity.TEXTURE_EAST;
        };
    }
}

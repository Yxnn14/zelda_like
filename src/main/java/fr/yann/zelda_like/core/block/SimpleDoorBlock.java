package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.inventory.KeyItem;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class SimpleDoorBlock extends AbstractDoorBlock {

    private static final Image TEXTURE_LOCK = new Image("assets/textures/door_lock.png");
    private static final Image TEXTURE_OPEN = new Image("assets/textures/door_open.png");
    private static final Image TEXTURE_LOCK_TURNED = new Image("assets/textures/door_lock_turned.png");
    private static final Image TEXTURE_OPEN_TURNED = new Image("assets/textures/door_open_turned.png");
    public SimpleDoorBlock(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, location, Color.color(0.5, 0, 0.5));
    }

    @Override
    public Image getTexture() {
        return switch (this.getLocation().getOrientation()) {
            case SOUTH, NORTH -> this.isOpen() ? SimpleDoorBlock.TEXTURE_OPEN : SimpleDoorBlock.TEXTURE_LOCK;
            case EAST, WEST -> this.isOpen() ? SimpleDoorBlock.TEXTURE_OPEN_TURNED : SimpleDoorBlock.TEXTURE_LOCK_TURNED;
        };
    }

    @Override
    public boolean interact(Entity entity) {
        if (!(entity instanceof PlayerEntity)) {
            return false;
        }
        if (!entity.getLocation().getOrientation().equals(this.getLocation().getOrientation())) {
            return false;
        }
        if (!((PlayerEntity) entity).getInventory().contains(KeyItem.class)) {
            return false;
        }
        return super.interact(entity);
    }
}

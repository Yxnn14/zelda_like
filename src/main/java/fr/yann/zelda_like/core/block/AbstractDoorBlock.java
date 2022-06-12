package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.block.DoorBlock;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public class AbstractDoorBlock extends AbstractBlock implements DoorBlock {

    private boolean open;

    protected AbstractDoorBlock(ZeldaLike zeldaLike, Location location, Color color) {
        super(zeldaLike, location, color);
    }

    protected AbstractDoorBlock(ZeldaLike zeldaLike, Location location, Color color, boolean transparent) {
        super(zeldaLike, location, color, transparent);
    }

    @Override
    public boolean isOpen() {
        return open;
    }

    @Override
    public void setOpen(boolean open) {
        this.open = open;
    }

    @Override
    public boolean interact(Entity entity) {
        this.open = !this.open;
        return true;
    }

    @Override
    public boolean isTransparent() {
        return this.isOpen();
    }
}

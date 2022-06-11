package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public class AbstractDoorBlock extends AbstractBlock {

    private boolean open;

    protected AbstractDoorBlock(ZeldaLike zeldaLike, Location location, Color color) {
        super(zeldaLike, location, color);
    }

    protected AbstractDoorBlock(ZeldaLike zeldaLike, Location location, Color color, boolean transparent) {
        super(zeldaLike, location, color, transparent);
    }

    public boolean isOpen() {
        return open;
    }

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

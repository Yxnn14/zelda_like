package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.api.updater.UpdaterManager;
import fr.yann.zelda_like.core.updater.ImplUpdaterManager;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class AbstractBlock implements Block {

    protected final Location location;
    protected final Color color;

    protected final boolean transparent;

    protected final UpdaterManager<Block> updaterManager;

    protected AbstractBlock(ZeldaLike zeldaLike, Location location, Color color) {
        this(zeldaLike, location, color, true);
    }

    protected AbstractBlock(ZeldaLike zeldaLike, Location location, Color color, boolean transparent) {
        this.location = location;
        this.color = color;
        this.updaterManager = new ImplUpdaterManager<>(zeldaLike, this);
        this.transparent = transparent;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public boolean isTransparent() {
        return this.transparent;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public UpdaterManager<Block> getUpdaterManager() {
        return this.updaterManager;
    }

    @Override
    public boolean interact(Entity entity) {
        return false;
    }

    @Override
    public Image getTexture() {
        return null;
    }
}

package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public class AbstractBlock implements Block {

    private final Location location;
    protected final Color color;

    protected AbstractBlock(Location location, Color color) {
        this.location = location;
        this.color = color;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}

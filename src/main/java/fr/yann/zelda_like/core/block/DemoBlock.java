package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public class DemoBlock extends AbstractBlock {

    public DemoBlock(Location location) {
        super(location, Color.color(0.5, 0, 0.5));
    }
}

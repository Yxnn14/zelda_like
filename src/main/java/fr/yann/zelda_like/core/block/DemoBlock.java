package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public class DemoBlock extends AbstractBlock {

    public DemoBlock(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, location, Color.color(0.5, 0, 0.5));
    }
}

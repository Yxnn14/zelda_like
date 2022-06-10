package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public class DemoTwoBlock extends AbstractBlock {
    public DemoTwoBlock(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, location, Color.color(0, 0.5, 0.5), false);
    }
}
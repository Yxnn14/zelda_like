package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BarrierBlock extends AbstractBlock {
    public BarrierBlock(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, location, Color.color(0, 0, 0), false);
    }
}

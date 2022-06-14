package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.updater.block.DemoBlockUpdater;
import javafx.scene.paint.Color;

public class DemoThreeBlock extends AbstractBlock {

    public DemoThreeBlock(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, location, Color.color(0, 0.5, 0));
        this.getUpdaterManager().add(new DemoBlockUpdater());
    }
}

package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public class DemoThreeBlock extends AbstractBlock {

    public DemoThreeBlock(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, location, Color.color(0, 0.5, 0));
        this.getUpdaterManager()
            .add((zeldaLike1, block) -> {
                final Entity entity = zeldaLike1.getLevelManager().get()
                    .getEntityAt(block.getLocation().getX(), block.getLocation().getY());
                if (entity != null) {
                    System.out.println(entity.getName());
                }
            });
    }
}

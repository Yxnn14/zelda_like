package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class WallBlock extends AbstractBlock {
    private static final Image TEXTURE = new Image("assets/textures/wall.png");

    public WallBlock(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, location, Color.color(0, 0.5, 0.5), false);
    }

    @Override
    public Image getTexture() {
        return WallBlock.TEXTURE;
    }
}

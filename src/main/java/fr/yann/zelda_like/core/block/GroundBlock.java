package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class GroundBlock extends AbstractBlock {

    public static final Image TEXTURE = new Image("assets/textures/ground.png");

    public GroundBlock(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, location, Color.color(0.5, 0, 0.5));
    }

    @Override
    public Image getTexture() {
        return GroundBlock.TEXTURE;
    }
}


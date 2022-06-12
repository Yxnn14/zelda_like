package fr.yann.zelda_like.core.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.updater.block.BombBlockUpdater;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BombBlock extends AbstractBlock {

    private static final Image TEXTURE = new Image("assets/textures/bomb_block.png");

    public BombBlock(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, location, Color.color(0.5, 0, 0.5));
        this.getUpdaterManager()
                .add(new BombBlockUpdater(5));
    }
    @Override
    public Image getTexture() {
        return BombBlock.TEXTURE;
    }
}

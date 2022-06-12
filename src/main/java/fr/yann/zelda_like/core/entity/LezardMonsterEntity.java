package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class LezardMonsterEntity extends AbstractMonsterEntity {

    private static final Image TEXTURE_SOUTH = new Image("assets/textures/lezard_south.png");
    private static final Image TEXTURE_NORTH = new Image("assets/textures/lezard_north.png");
    private static final Image TEXTURE_WEST = new Image("assets/textures/lezard_west.png");
    private static final Image TEXTURE_EAST = new Image("assets/textures/lezard_east.png");

    public LezardMonsterEntity(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, "Lezard", location, Color.color(0.153, 0.08, 0.5), 10);
    }

    @Override
    public Image getTexture() {
        return switch (this.getLocation().getOrientation()) {
            case SOUTH -> LezardMonsterEntity.TEXTURE_SOUTH;
            case NORTH -> LezardMonsterEntity.TEXTURE_NORTH;
            case WEST -> LezardMonsterEntity.TEXTURE_WEST;
            case EAST -> LezardMonsterEntity.TEXTURE_EAST;
        };
    }
}

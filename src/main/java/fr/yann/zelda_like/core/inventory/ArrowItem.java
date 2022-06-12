package fr.yann.zelda_like.core.inventory;

import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.entity.ImplPlayerEntity;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ArrowItem extends AbstractBulletItem {

    private static final Image TEXTURE_NORTH = new Image("assets/textures/arrow_north.png");
    private static final Image TEXTURE_SOUTH = new Image("assets/textures/arrow_south.png");
    private static final Image TEXTURE_EAST = new Image("assets/textures/arrow_east.png");
    private static final Image TEXTURE_WEST = new Image("assets/textures/arrow_west.png");

    public ArrowItem() {
        super("Arrow", Color.color(0.02, 0.9, 0.9), 5, 2, 1);
    }

    @Override
    public Image getTexture(Location.Orientation orientation) {
        return switch (orientation) {
            case SOUTH -> ArrowItem.TEXTURE_SOUTH;
            case NORTH -> ArrowItem.TEXTURE_NORTH;
            case WEST -> ArrowItem.TEXTURE_WEST;
            case EAST -> ArrowItem.TEXTURE_EAST;
        };
    }
}

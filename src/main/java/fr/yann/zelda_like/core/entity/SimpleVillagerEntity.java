package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.dialog.Dialog;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.dialog.ImplDialog;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class SimpleVillagerEntity extends AbstractVillagerEntity {

    private static final Image TEXTURE_WEST = new Image("assets/textures/villager_west.png");
    private static final Image TEXTURE_EAST = new Image("assets/textures/villager_east.png");

    public SimpleVillagerEntity(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, "Simple", location, Color.color(0.3, 0.5 , 0.7), 1);
        this.invulnerable = true;
    }

    @Override
    public Image getTexture() {
        return switch (this.getLocation().getOrientation()) {
            case WEST, NORTH -> SimpleVillagerEntity.TEXTURE_WEST;
            case EAST, SOUTH -> SimpleVillagerEntity.TEXTURE_EAST;
        };
    }
}

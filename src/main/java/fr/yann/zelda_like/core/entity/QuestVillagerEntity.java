package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class QuestVillagerEntity extends AbstractMerchantEntity {
    private static final Image TEXTURE_WEST = new Image("assets/textures/quest_west.png");
    private static final Image TEXTURE_EAST = new Image("assets/textures/quest_east.png");

    public QuestVillagerEntity(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, "possesseur de la clef", location, Color.color(0.2, 0.5, 0.8), 1);
        this.invulnerable = true;
    }


    @Override
    public Image getTexture() {
        return switch (this.getLocation().getOrientation()) {
            case WEST, NORTH -> QuestVillagerEntity.TEXTURE_WEST;
            case EAST, SOUTH -> QuestVillagerEntity.TEXTURE_EAST;
        };
    }
}

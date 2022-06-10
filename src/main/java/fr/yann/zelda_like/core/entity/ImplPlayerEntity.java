package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.updater.entity.ControllerUpdater;
import javafx.scene.paint.Color;

public class ImplPlayerEntity extends AbstractEntity implements PlayerEntity {
    public ImplPlayerEntity(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, "Player", location, Color.color(0.5, 0.5, 0));
        this.getUpdaterManager().add(new ControllerUpdater());
    }
}

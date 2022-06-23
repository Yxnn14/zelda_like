package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public class AngleEntity extends AbstractMonsterEntity {
    public AngleEntity(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, "Angle", location, Color.color(0.0, 1.0, 0.0), 1);
        this.invulnerable = true;
    }
}

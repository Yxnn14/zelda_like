package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public class GivePNJ extends AbstractMerchantEntity {
    public GivePNJ(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, "Give", location, Color.color(0.2, 0.5, 0.8), 1);
        this.invulnerable = true;
    }
}

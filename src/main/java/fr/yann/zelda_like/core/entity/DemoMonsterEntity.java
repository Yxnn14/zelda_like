package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public class DemoMonsterEntity extends AbstractMonsterEntity {
    public DemoMonsterEntity(ZeldaLike zeldaLike, Location location) {
        super(zeldaLike, "mob", location, Color.color(0.153, 0.08, 0.5), 10);
    }
}

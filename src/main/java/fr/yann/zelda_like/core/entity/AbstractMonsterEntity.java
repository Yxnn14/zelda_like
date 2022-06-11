package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.MonsterEntity;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public abstract class AbstractMonsterEntity extends AbstractEntity implements MonsterEntity {
    protected AbstractMonsterEntity(ZeldaLike zeldaLike, String name, Location location, Color color, int health) {
        super(zeldaLike, name, location, color, health);
    }
}

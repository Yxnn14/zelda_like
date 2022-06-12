package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.BulletEntity;
import fr.yann.zelda_like.api.entity.MonsterEntity;
import fr.yann.zelda_like.api.inventory.BulletItem;
import fr.yann.zelda_like.api.level.Location;
import javafx.scene.paint.Color;

public abstract class AbstractMonsterEntity extends AbstractEntity implements MonsterEntity {
    protected AbstractMonsterEntity(ZeldaLike zeldaLike, String name, Location location, Color color, int health) {
        super(zeldaLike, name, location, color, health);
    }

    @Override
    public void shoot(BulletItem bulletItem) {
        int x = 0;
        int y = 0;

        switch (this.getLocation().getOrientation()) {
            case EAST -> x = 1;
            case WEST -> x = -1;
            case NORTH -> y = -1;
            case SOUTH -> y = 1;
        }
        BulletEntity bulletEntity = this.zeldaLike.getLevelManager().get()
                .spawn(ImplBulletEntity.class, this.getLocation().add(x, y));
        bulletEntity.setItem(bulletItem);
    }
}

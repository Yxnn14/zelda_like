package fr.yann.zelda_like.api.entity;

import fr.yann.zelda_like.api.inventory.BulletItem;

public interface ShooterEntity extends Entity {
    void shoot(BulletItem bulletItem);
}

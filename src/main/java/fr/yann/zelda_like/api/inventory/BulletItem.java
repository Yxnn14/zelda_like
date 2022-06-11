package fr.yann.zelda_like.api.inventory;

public interface BulletItem extends Item {

    int getRange();
    int getDamage();
    int getSpeed();
}

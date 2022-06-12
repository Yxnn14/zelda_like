package fr.yann.zelda_like.api.entity;

public interface BulletEntity extends ItemEntity {
    Entity getShooter();
    void setShooter(Entity entity);
}

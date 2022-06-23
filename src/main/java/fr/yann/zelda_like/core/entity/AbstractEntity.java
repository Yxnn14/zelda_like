package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.api.updater.UpdaterManager;
import fr.yann.zelda_like.core.updater.ImplUpdaterManager;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class AbstractEntity implements Entity {
    private final UpdaterManager<Entity> updaterManager;
    private final String name;
    private final Color color;

    private Location location;
    protected ZeldaLike zeldaLike;

    protected int health;
    protected boolean invulnerable;
    protected boolean visible = true;


    protected AbstractEntity(ZeldaLike zeldaLike, String name, Location location, Color color, int health) {
        this.zeldaLike = zeldaLike;
        this.name = name;
        this.location = location;
        this.color = color;
        this.updaterManager = new ImplUpdaterManager<>(zeldaLike, this);
        this.health = health;
    }

    @Override
    public ZeldaLike getZeldaLike() {
        return this.zeldaLike;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public UpdaterManager<Entity> getUpdaterManager() {
        return this.updaterManager;
    }

    @Override
    public int getHealth() {
        return this.invulnerable ? 1 : this.health;
    }

    @Override
    public void addHealth(int health) {
        this.health = Math.max(this.invulnerable ? 1 : 0, this.health + health);
    }

    @Override
    public void removeHealth(int health) {
        this.health = Math.max(this.invulnerable ? 1 : 0, this.health - health);
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public boolean interact(Entity entity) {
        return false;
    }

    @Override
    public Image getTexture() {
        return null;
    }

    @Override
    public boolean isInvulnerable() {
        return this.invulnerable;
    }

    @Override
    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    @Override
    public boolean isDeath() {
        return this.health <= 0;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void killedBy(Entity entity) {
    }
}

package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.api.updater.UpdaterManager;
import fr.yann.zelda_like.core.updater.ImplUpdaterManager;
import javafx.scene.paint.Color;

public abstract class AbstractEntity implements Entity {
    private final UpdaterManager<Entity> updaterManager;
    private final String name;
    private final Color color;

    private Location location;

    protected int health;


    protected AbstractEntity(ZeldaLike zeldaLike, String name, Location location, Color color, int health) {
        this.name = name;
        this.location = location;
        this.color = color;
        this.updaterManager = new ImplUpdaterManager<>(zeldaLike, this);
        this.health = health;
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
        return this.health;
    }
}

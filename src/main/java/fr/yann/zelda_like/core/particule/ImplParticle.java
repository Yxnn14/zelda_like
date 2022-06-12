package fr.yann.zelda_like.core.particule;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.particule.Particle;
import fr.yann.zelda_like.api.particule.Vector;
import fr.yann.zelda_like.api.updater.UpdaterManager;
import fr.yann.zelda_like.core.updater.ImplUpdaterManager;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ImplParticle implements Particle {

    private final UpdaterManager<Particle> updaterManager;
    private final Vector location;
    private final Image texture;
    private final Color color;
    private final int lifetime;

    private Vector velocity;

    public ImplParticle(ZeldaLike zeldaLike, Color color, Vector location, int lifetime) {
        this(zeldaLike, null, color, location, lifetime);
    }

    public ImplParticle(ZeldaLike zeldaLike, Image texture, Vector location, int lifetime) {
        this(zeldaLike, texture, Color.color(0, 0, 0), location, lifetime);
    }

    public ImplParticle(ZeldaLike zeldaLike, Image texture, Color color, Vector location, int lifetime) {
        this.location = location;
        this.texture = texture;
        this.color = color;
        this.lifetime = lifetime;
        this.updaterManager = new ImplUpdaterManager<>(zeldaLike, this);
    }

    @Override
    public Image getTexture() {
        return this.texture;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public Vector getLocation() {
        return this.location;
    }

    @Override
    public Vector getVelocity() {
        return this.velocity;
    }

    @Override
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    @Override
    public int getLifetime() {
        return this.lifetime;
    }

    @Override
    public UpdaterManager<Particle> getUpdaterManager() {
        return this.updaterManager;
    }
}

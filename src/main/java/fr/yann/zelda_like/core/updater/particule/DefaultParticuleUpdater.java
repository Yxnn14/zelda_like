package fr.yann.zelda_like.core.updater.particule;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.particule.Particle;
import fr.yann.zelda_like.api.updater.ParticuleUpdater;
import fr.yann.zelda_like.core.particule.ImplVector;

import java.util.Random;

public class DefaultParticuleUpdater implements ParticuleUpdater {

    private int lifetime;

    @Override
    public void update(ZeldaLike zeldaLike, Particle particle) {
        if (this.lifetime >= particle.getLifetime()) {
            zeldaLike.getLevelManager().get().removeParticle(particle);
            return;
        }

        if (particle.getVelocity() == null) {
            final Random random = new Random();
            particle.setVelocity(
                ImplVector.create(
                    random.nextFloat(40.0f) - 20.f,
                    random.nextFloat(40.0f) - 20.f
                )
            );
        }

        particle.getLocation().apply(particle.getVelocity());
        particle.getVelocity().multiply(.85f);
        this.lifetime++;
    }

    @Override
    public ParticuleUpdater clone() {
        return new DefaultParticuleUpdater();
    }
}

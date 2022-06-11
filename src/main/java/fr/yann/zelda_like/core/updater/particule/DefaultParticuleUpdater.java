package fr.yann.zelda_like.core.updater.particule;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.particule.Particule;
import fr.yann.zelda_like.api.updater.ParticuleUpdater;
import fr.yann.zelda_like.core.particule.ImplVector;

import java.util.Random;

public class DefaultParticuleUpdater implements ParticuleUpdater {

    private int lifetime;

    @Override
    public void update(ZeldaLike zeldaLike, Particule particule) {
        if (this.lifetime >= particule.getLifetime()) {
            zeldaLike.getLevelManager().get().removeParticule(particule);
            return;
        }

        if (particule.getVelocity() == null) {
            final Random random = new Random();
            particule.setVelocity(
                ImplVector.create(
                    random.nextFloat(40.0f) - 20.f,
                    random.nextFloat(40.0f) - 20.f
                )
            );
        }

        particule.getLocation().apply(particule.getVelocity());
        particule.getVelocity().multiply(.85f);
        this.lifetime++;
    }

    @Override
    public ParticuleUpdater clone() {
        return new DefaultParticuleUpdater();
    }
}

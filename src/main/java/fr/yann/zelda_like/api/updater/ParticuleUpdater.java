package fr.yann.zelda_like.api.updater;

import fr.yann.zelda_like.api.particule.Particle;

public interface ParticuleUpdater extends Updater<Particle> {
    ParticuleUpdater clone();
}

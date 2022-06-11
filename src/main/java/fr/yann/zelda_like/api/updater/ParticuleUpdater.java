package fr.yann.zelda_like.api.updater;

import fr.yann.zelda_like.api.particule.Particule;

public interface ParticuleUpdater extends Updater<Particule> {
    ParticuleUpdater clone();
}

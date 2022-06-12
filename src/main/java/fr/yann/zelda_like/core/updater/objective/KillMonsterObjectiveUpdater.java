package fr.yann.zelda_like.core.updater.objective;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.MonsterEntity;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.objective.Objective;
import fr.yann.zelda_like.api.updater.Updater;

public class KillMonsterObjectiveUpdater implements Updater<Objective> {
    @Override
    public void update(ZeldaLike zeldaLike, Objective objective) {
        if (objective.isComplete()) {
            objective.getUpdaterManager().remove(this.getClass());
            return;
        }
        final Level level = zeldaLike.getLevelManager().get();
        if (level.getEntities(MonsterEntity.class).isEmpty()) {
            objective.setComplete(true);
        }
    }
}

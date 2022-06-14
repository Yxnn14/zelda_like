package fr.yann.zelda_like.core.updater.objective;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.objective.Objective;
import fr.yann.zelda_like.api.updater.Updater;
import fr.yann.zelda_like.core.inventory.KeyItem;

public class KeyObjectiveUpdater implements Updater<Objective> {
    @Override
    public void update(ZeldaLike zeldaLike, Objective objective) {
        if (objective.isComplete()) {
            objective.getUpdaterManager().remove(this.getClass());
            return;
        }

        if (zeldaLike.getLevelManager().get().getPlayer().getInventory().contains(KeyItem.class)) {
            objective.setComplete(true);
        }
    }
}

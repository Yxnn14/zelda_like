package fr.yann.zelda_like.core.updater.objective;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.objective.Objective;
import fr.yann.zelda_like.api.updater.Updater;

import java.util.function.Consumer;

public class CompleteAllObjectiveUpdater implements Updater<Objective> {
    private final Consumer<ZeldaLike> complete;

    public CompleteAllObjectiveUpdater(Consumer<ZeldaLike> complete) {
        this.complete = complete;
    }

    @Override
    public void update(ZeldaLike zeldaLike, Objective objective) {
        if (objective.isComplete()) {
            this.complete.accept(zeldaLike);
            objective.getUpdaterManager().remove(this.getClass());
            return;
        }

        objective.setComplete(
            zeldaLike.getLevelManager().get()
                .getObjectiveManager()
                .getObjectives()
                .stream()
                .filter(currentObjective -> !currentObjective.equals(objective))
                .allMatch(Objective::isComplete)
        );
    }
}

package fr.yann.zelda_like.core.objective;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.objective.Objective;
import fr.yann.zelda_like.api.objective.ObjectiveManager;

import java.util.ArrayList;
import java.util.List;

public class ImplObjectiveManager implements ObjectiveManager {
    private final List<Objective> objectives = new ArrayList<>();
    private final ZeldaLike zeldaLike;

    public ImplObjectiveManager(ZeldaLike zeldaLike) {
        this.zeldaLike = zeldaLike;
    }

    @Override
    public List<Objective> getObjectives() {
        return new ArrayList<>(this.objectives);
    }

    @Override
    public void add(Objective objective) {
        this.objectives.add(objective);
    }

    @Override
    public void remove(Objective objective) {
        this.objectives.remove(objective);
    }

    @Override
    public Objective create(String title, String description) {
        return new ImplObjective(this.zeldaLike, title, description);
    }
}

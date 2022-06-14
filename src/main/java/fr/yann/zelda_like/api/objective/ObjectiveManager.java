package fr.yann.zelda_like.api.objective;

import java.util.List;

public interface ObjectiveManager
{
    List<Objective> getObjectives();

    ObjectiveManager add(Objective objective);

    void remove(Objective objective);

    Objective create(String title, String description);
}

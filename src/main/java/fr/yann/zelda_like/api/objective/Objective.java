package fr.yann.zelda_like.api.objective;

import fr.yann.zelda_like.api.updater.UpdaterManager;

public interface Objective {
    String getTitle();

    void setTitle(String title);

    String getDescription();

    void setDescription(String description);

    boolean isComplete();

    void setComplete(boolean complete);

    UpdaterManager<Objective> getUpdaterManager();
}

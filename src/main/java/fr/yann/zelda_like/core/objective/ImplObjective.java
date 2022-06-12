package fr.yann.zelda_like.core.objective;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.objective.Objective;
import fr.yann.zelda_like.api.updater.UpdaterManager;
import fr.yann.zelda_like.core.updater.ImplUpdaterManager;

public class ImplObjective implements Objective {

    private final UpdaterManager<Objective> updaterManager;
    private String title;
    private String description;

    private boolean complete;

    protected ImplObjective(ZeldaLike zeldaLike, String title, String description) {
        this.title = title;
        this.description = description;
        this.updaterManager = new ImplUpdaterManager<>(zeldaLike, this);
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean isComplete() {
        return this.complete;
    }

    @Override
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public UpdaterManager<Objective> getUpdaterManager() {
        return this.updaterManager;
    }
}

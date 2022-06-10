package fr.yann.zelda_like.core.updater;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.updater.Updater;
import fr.yann.zelda_like.api.updater.UpdaterManager;

import java.util.ArrayList;
import java.util.List;

public class ImplUpdaterManager<T> implements UpdaterManager<T> {
    private final List<Updater<T>> updaters = new ArrayList<>();
    private final ZeldaLike zeldaLike;
    private final T type;

    public ImplUpdaterManager(ZeldaLike zeldaLike, T type) {
        this.zeldaLike = zeldaLike;
        this.type = type;
    }

    @Override
    public UpdaterManager<T> add(Updater<T> updater) {
        this.updaters.add(updater);
        return this;
    }

    @Override
    public void update() {
        this.updaters.forEach(updater -> updater.update(this.zeldaLike, this.type));
    }
}

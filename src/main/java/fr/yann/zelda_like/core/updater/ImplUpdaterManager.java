package fr.yann.zelda_like.core.updater;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.updater.ResetUpdater;
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
        if (updater instanceof ResetUpdater<T>) {
            for (Updater<T> currentUpdater : this.updaters) {
                if (currentUpdater.getClass().equals(updater.getClass())) {
                    ((ResetUpdater<T>) currentUpdater).reset();;
                    return this;
                }
            }
        }
        this.updaters.add(updater);
        return this;
    }

    @Override
    public void update() {
        new ArrayList<>(this.updaters)
            .forEach(updater -> updater.update(this.zeldaLike, this.type));
    }

    @Override
    public UpdaterManager<T> remove(Class<? extends Updater<T>> updaterClass) {
        final List<Updater<T>> list = new ArrayList<>(this.updaters);
        for (Updater<T> updater : list) {
            if (updater.getClass().equals(updaterClass)) {
                this.updaters.remove(updater);
                return this;
            }
        }
        return this;
    }
}

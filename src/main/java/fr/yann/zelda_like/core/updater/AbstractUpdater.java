package fr.yann.zelda_like.core.updater;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.updater.Updater;

public abstract class AbstractUpdater<T> implements Updater<T> {

    private final int tickUpdater;
    private int nextUpdate;

    protected AbstractUpdater(int tickUpdater) {
        this.tickUpdater = tickUpdater;
    }

    @Override
    public final void update(ZeldaLike zeldaLike, T type) {
        if (this.nextUpdate > 0) {
            this.nextUpdate--;
            return;
        }
        this.onUpdate(zeldaLike, type);
        this.nextUpdate = this.tickUpdater;
    }

    public abstract void onUpdate(ZeldaLike zeldaLike, T type);
}

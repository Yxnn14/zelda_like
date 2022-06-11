package fr.yann.zelda_like.api.updater;

public interface UpdaterManager<T> {
    UpdaterManager<T> add(Updater<T> updater);
    UpdaterManager<T> remove(Class<? extends Updater<T>> updaterClass);
    void update();
}

package fr.yann.zelda_like.api.updater;

public interface UpdaterManager<T> {
    UpdaterManager<T> add(Updater<T> updater);
    void update();
}

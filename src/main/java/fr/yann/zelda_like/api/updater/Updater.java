package fr.yann.zelda_like.api.updater;

import fr.yann.zelda_like.api.ZeldaLike;

public interface Updater<T> {
    void update(ZeldaLike zeldaLike, T type);
}

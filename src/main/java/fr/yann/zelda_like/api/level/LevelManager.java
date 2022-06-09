package fr.yann.zelda_like.api.level;

import fr.yann.zelda_like.api.render.LevelRender;

public interface LevelManager {
    Level get();
    void load(Class<? extends Level> levelClass);
    LevelRender getRender();
}

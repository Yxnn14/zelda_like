package fr.yann.zelda_like.api.level;

public interface LevelManager {
    Level get();
    void load(Class<? extends Level> levelClass);
}

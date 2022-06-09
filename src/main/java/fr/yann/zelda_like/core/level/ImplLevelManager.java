package fr.yann.zelda_like.core.level;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Player;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.LevelManager;
import fr.yann.zelda_like.core.ImplZeldaLike;

import java.lang.reflect.InvocationTargetException;

public class ImplLevelManager implements LevelManager {

    private final ZeldaLike zeldaLike;
    private Level level;

    public ImplLevelManager(ZeldaLike zeldaLike) {
        this.zeldaLike = zeldaLike;
    }

    @Override
    public Level get() {
        return this.level;
    }

    @Override
    public void load(Class<? extends Level> levelClass) {
        try {
            this.level = levelClass.getConstructor(ZeldaLike.class).newInstance(this.zeldaLike);
        } catch (
            InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e
        ) {
            throw new RuntimeException(e);
        }
    }
}

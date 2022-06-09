package fr.yann.zelda_like.core.level;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Player;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.LevelManager;
import fr.yann.zelda_like.api.render.LevelRender;
import fr.yann.zelda_like.core.ImplZeldaLike;
import fr.yann.zelda_like.core.render.ImplLevelRender;

import java.lang.reflect.InvocationTargetException;

public class ImplLevelManager implements LevelManager {

    private final ZeldaLike zeldaLike;
    private Level level;
    private final LevelRender render;

    public ImplLevelManager(ZeldaLike zeldaLike) {
        this.zeldaLike = zeldaLike;
        this.render = new ImplLevelRender(zeldaLike);
    }

    @Override
    public Level get() {
        return this.level;
    }

    @Override
    public void load(Class<? extends Level> levelClass) {
        try {
            this.level = levelClass.getConstructor(ZeldaLike.class).newInstance(this.zeldaLike);
            this.level.getGenerator().generate(this.level);
            this.render.render();
        } catch (
            InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e
        ) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LevelRender getRender() {
        return this.render;
    }
}

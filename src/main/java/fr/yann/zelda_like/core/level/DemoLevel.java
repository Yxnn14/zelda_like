package fr.yann.zelda_like.core.level;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.core.updater.level.LevelUpdater;

public class DemoLevel extends AbstractLevel {
    public DemoLevel(ZeldaLike zeldaLike) {
        super(zeldaLike, 64, 36, new DemoLevelGenerator());
        this.getUpdaterManager().add(new LevelUpdater());
    }
}

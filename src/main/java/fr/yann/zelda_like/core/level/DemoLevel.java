package fr.yann.zelda_like.core.level;

import fr.yann.zelda_like.api.ZeldaLike;

public class DemoLevel extends AbstractLevel {
    public DemoLevel(ZeldaLike zeldaLike) {
        super(zeldaLike, 16, 9, new DemoLevelGenerator());
    }
}

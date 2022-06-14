package fr.yann.zelda_like.core.level;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.core.dialog.ImplDialogManager;
import fr.yann.zelda_like.core.level.generator.DemoLevelGenerator;
import fr.yann.zelda_like.core.updater.level.LevelUpdater;

public class DemoLevel extends AbstractLevel {
    public DemoLevel(ZeldaLike zeldaLike) {
        super(zeldaLike, 16, 9, new DemoLevelGenerator(), new ImplDialogManager(zeldaLike, 200));
        this.getUpdaterManager().add(new LevelUpdater());
    }
}

package fr.yann.zelda_like.core.level;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.core.dialog.ImplDialogManager;
import fr.yann.zelda_like.core.level.generator.OneLevelGenerator;
import fr.yann.zelda_like.core.updater.level.LevelUpdater;

public class OneLevel extends AbstractLevel {
    public OneLevel(ZeldaLike zeldaLike) {
        super(zeldaLike, 32, 18, new OneLevelGenerator(), new ImplDialogManager(zeldaLike, 200));
        this.getUpdaterManager().add(new LevelUpdater());
    }
}

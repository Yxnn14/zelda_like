package fr.yann.zelda_like.core.updater.level;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.updater.Updater;

public class LevelUpdater implements Updater<Level> {
    @Override
    public void update(ZeldaLike zeldaLike, Level level) {
        if (!level.isPause()) {
            level.getEntities().forEach(entity -> entity.getUpdaterManager().update());
            level.getBlocks().forEach(block -> block.getUpdaterManager().update());
        }
        level.getDialogManager().getUpdaterManager().update();
    }
}

package fr.yann.zelda_like.core.updater.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.updater.Updater;

public class LevelOneVictoryUpdater implements Updater<Block> {
    @Override
    public void update(ZeldaLike zeldaLike, Block block) {
        final Entity entity = zeldaLike.getLevelManager().get().getEntityAt(block.getLocation());
        if (entity instanceof PlayerEntity player) {
            block.getUpdaterManager().remove(this.getClass());
            zeldaLike.getLevelManager().get().setVictory(true);
        }
    }
}

package fr.yann.zelda_like.core.updater.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.block.TeleportBlock;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.updater.Updater;
import fr.yann.zelda_like.core.updater.particule.DefaultParticuleUpdater;
import javafx.scene.paint.Color;

public class TeleportUpdater implements Updater<Block> {

    @Override
    public void update(ZeldaLike zeldaLike, Block block) {
        if (!(block instanceof TeleportBlock)) {
            return;
        }
        final Level level = zeldaLike.getLevelManager().get();
        final Entity entity = zeldaLike.getLevelManager().get().getEntityAt(block.getLocation());
        if (!(entity instanceof PlayerEntity)) {
            return;
        }
        if (
            ((TeleportBlock) block).getTeleportLocation() != null
                && level.moveEntity(entity, ((TeleportBlock) block).getTeleportLocation().clone())
        ) {
            level.addParticles(
                Color.color(0, 0, 1),
                block.getLocation(),
                200,
                new DefaultParticuleUpdater(),
                20
            );
        }

    }
}

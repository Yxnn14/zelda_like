package fr.yann.zelda_like.core.updater.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.updater.Updater;
import fr.yann.zelda_like.core.block.GroundBlock;
import fr.yann.zelda_like.core.level.ImplLocation;
import fr.yann.zelda_like.core.updater.particule.DefaultParticuleUpdater;
import javafx.scene.paint.Color;

public class BombBlockUpdater implements Updater<Block> {
    private final int damage;
    public BombBlockUpdater(int damage) {
        this.damage = damage;
    }
    @Override
    public void update(ZeldaLike zeldaLike, Block block) {
        final Level level = zeldaLike.getLevelManager().get();
        final Entity entity = level.getEntityAt(block.getLocation());
        if (entity instanceof PlayerEntity playerEntity) {
            level.addParticles(
                    Color.color(1d, 0d, 0d),
                    block.getLocation(),
                    10,
                    new DefaultParticuleUpdater(),
                    10
            );
            level.damageEntity(playerEntity, this.damage);
            level.setBlock(GroundBlock.class, block.getLocation());
        }

    }
}

package fr.yann.zelda_like.api.level;

import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.updater.UpdaterManager;

import java.util.List;

public interface Level {

    int getWidth();

    int getHeight();

    PlayerEntity getPlayer();

    List<Block> getBlocks();

    Block getBlockAt(int x, int y);

    default Block getBlockAt(Location location) {
        return this.getBlockAt(location.getX(), location.getY());
    }

    void setBlock(Class<? extends Block> blockClass, Location location);

    List<Entity> getEntities();

    Entity getEntityAt(int x, int y);

    default Entity getEntityAt(Location location) {
        return this.getEntityAt(location.getX(), location.getY());
    }

    <T extends Entity> T spawn(Class<T> entityClazz, Location location);

    void moveEntity(Entity entity, Location location);

    void removeEntity(Entity entity);

    LevelGenerator getGenerator();

    UpdaterManager<Level> getUpdaterManager();
}

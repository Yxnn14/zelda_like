package fr.yann.zelda_like.api.level;

import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.Player;

import java.util.List;

public interface Level {

    int getWidth();

    int getHeight();

    Player getPlayer();

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

    Entity spawn(Class<? extends Entity> entityClazz, Location location);

    LevelGenerator getGenerator();
}

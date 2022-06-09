package fr.yann.zelda_like.core.level;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.Player;
import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractLevel implements Level {

    protected final Block[][] blocks;
    protected final Entity[][] entities;

    protected final ZeldaLike zeldaLike;

    protected Player player;

    protected AbstractLevel(ZeldaLike zeldaLike, int width, int height) {
        this.zeldaLike = zeldaLike;
        this.blocks = new Block[width][height];
        this.entities = new Entity[width][height];
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }

    @Override
    public List<Block> getBlocks() {
        return this.getObjects(this.blocks);
    }

    @Override
    public Block getBlockAt(int x, int y) {
        return this.getObjectAt(this.blocks, x, y);
    }

    @Override
    public void setBlock(Class<? extends Block> blockClass, Location location) {

    }

    @Override
    public List<Entity> getEntities() {
        return this.getObjects(this.entities);
    }

    @Override
    public Entity getEntityAt(int x, int y) {
        return this.getObjectAt(this.entities, x, y);
    }

    @Override
    public Entity spawn(Class<? extends Entity> entityClazz, Location location) {
        return null;
    }

    private <T> T getObjectAt(T[][] objects, int x, int y) {
        final T[] object = objects[x % objects.length];
        return object[y % object.length];
    }

    private <T> List<T> getObjects(T[][] objects) {
        final List<T> list = new ArrayList<>();
        for (T[] object : objects) {
            Collections.addAll(list, object);
        }
        return list;
    }
}

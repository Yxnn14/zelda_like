package fr.yann.zelda_like.core.level;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.dialog.DialogManager;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.LevelGenerator;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.api.updater.UpdaterManager;
import fr.yann.zelda_like.core.block.BarrierBlock;
import fr.yann.zelda_like.core.updater.ImplUpdaterManager;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLevel implements Level {

    protected final Block[][] blocks;
    protected final Entity[][] entities;

    protected final ZeldaLike zeldaLike;

    protected final LevelGenerator generator;
    protected final DialogManager dialogManager;
    protected final UpdaterManager<Level> updaterManager;

    protected PlayerEntity player;
    protected boolean pause;

    protected AbstractLevel(
        ZeldaLike zeldaLike,
        int width,
        int height,
        LevelGenerator generator,
        DialogManager dialogManager
    ) {
        this.zeldaLike = zeldaLike;
        this.blocks = new Block[width][height];
        this.entities = new Entity[width][height];
        this.generator = generator;
        this.updaterManager = new ImplUpdaterManager<>(zeldaLike, this);
        this.dialogManager = dialogManager;
    }

    @Override
    public int getWidth() {
        return this.blocks.length;
    }

    @Override
    public int getHeight() {
        return this.blocks[0].length;
    }

    @Override
    public PlayerEntity getPlayer() {
        return this.player;
    }

    @Override
    public List<Block> getBlocks() {
        return this.getObjects(this.blocks);
    }

    @Override
    public Block getBlockAt(int x, int y) {
        final Block block = this.getObjectAt(this.blocks, x, y);
        return block != null ? block : new BarrierBlock(this.zeldaLike, ImplLocation.create(x, y));
    }

    @Override
    public void setBlock(Class<? extends Block> blockClass, Location location) {
        this.setObject(this.blocks, blockClass, location);
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
    public <T extends Entity> T spawn(Class<T> entityClazz, Location location) {
        final T entity = (T) this.setObject(this.entities, entityClazz, location);
        if (this.player == null && entity instanceof PlayerEntity player) {
            this.player = player;
        }
        return entity;
    }

    @Override
    public void removeEntity(Entity entity) {
        this.entities[entity.getLocation().getX()][entity.getLocation().getY()] = null;
    }

    @Override
    public void moveEntity(Entity entity, Location location) {
        if (location.getX() > -1 && location.getX() < this.entities.length) {
            Entity[] entities = this.entities[location.getX()];
            if (location.getY() > -1 && location.getY() < entities.length) {
                final Entity target = entities[location.getY()];
                if (target == null && this.blocks[location.getX()][location.getY()].isTransparent()) {
                    Entity lastEntity = this.entities[entity.getLocation().getX()][entity.getLocation().getY()];
                    if (lastEntity != null && lastEntity.equals(entity)) {
                        this.entities[entity.getLocation().getX()][entity.getLocation().getY()] = null;
                    }
                    entities[location.getY()] = entity;
                    entity.setLocation(location);
                }
            }
        }
    }

    private <T> T getObjectAt(T[][] objects, int x, int y) {
        if (x < 0 || x >= objects.length || y < 0 || objects[x].length >= y) {
            return null;
        }
        return objects[x][y];
    }

    private <T> List<T> getObjects(T[][] objects) {
        final List<T> list = new ArrayList<>();
        for (T[] object : objects) {
            for (T type : object) {
                if (type != null) {
                    list.add(type);
                }
            }
        }
        return list;
    }

    private <T> T setObject(T[][] object, Class<? extends T> clazz, Location location) {
        try {
            return object[location.getX()][location.getY()] = clazz
                .getConstructor(ZeldaLike.class, Location.class)
                .newInstance(this.zeldaLike, location);
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }
    }

    @Override
    public LevelGenerator getGenerator() {
        return this.generator;
    }

    public DialogManager getDialogManager() {
        return this.dialogManager;
    }

    @Override
    public UpdaterManager<Level> getUpdaterManager() {
        return this.updaterManager;
    }

    @Override
    public boolean isPause() {
        return this.pause;
    }

    @Override
    public void setPause(boolean pause) {
        this.pause = pause;
    }
}

package fr.yann.zelda_like.core.level;

import fr.yann.zelda_like.ZeldaLikeApplication;
import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.dialog.DialogManager;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.level.LevelGenerator;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.api.objective.ObjectiveManager;
import fr.yann.zelda_like.api.particule.Particle;
import fr.yann.zelda_like.api.updater.ParticuleUpdater;
import fr.yann.zelda_like.api.updater.UpdaterManager;
import fr.yann.zelda_like.core.block.BarrierBlock;
import fr.yann.zelda_like.core.objective.ImplObjectiveManager;
import fr.yann.zelda_like.core.particule.ImplParticle;
import fr.yann.zelda_like.core.particule.ImplVector;
import fr.yann.zelda_like.core.updater.ImplUpdaterManager;
import fr.yann.zelda_like.core.updater.animation.EntityDamageAnimationUpdater;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLevel implements Level {
    protected List<Particle> particles = new ArrayList<>();
    protected final Block[][] blocks;
    protected final Entity[][] entities;

    protected final ZeldaLike zeldaLike;

    protected final LevelGenerator generator;
    protected final DialogManager dialogManager;
    protected final UpdaterManager<Level> updaterManager;

    protected final ObjectiveManager objectiveManager;

    protected PlayerEntity player;
    protected boolean pause;
    protected boolean hudShow = true;
    protected boolean debugShow;

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
        this.objectiveManager = new ImplObjectiveManager(zeldaLike);
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
    public ObjectiveManager getObjectiveManager() {
        return this.objectiveManager;
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
    public <T extends Block> T setBlock(Class<T> blockClass, Location location) {
        return (T) this.setObject(this.blocks, blockClass, location);
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
        if (this.getEntityAt(location) != null) {
            return (T) this.setObject(
                this.entities,
                entityClazz,
                location.remove(location.getX() + 1, location.getY() + 1)
            );
        }
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
    public boolean moveEntity(Entity entity, Location location) {
        final Entity target = this.getEntityAt(location);
        if (target == null && this.getBlockAt(location).isTransparent()) {
            final Entity lastEntity = this.getEntityAt(entity.getLocation());;
            if (lastEntity != null && lastEntity.equals(entity)) {
                this.entities[entity.getLocation().getX()][entity.getLocation().getY()] = null;
            }
            this.entities[location.getX()][location.getY()] = entity;
            entity.setLocation(location);
            return true;
        }
        return false;
    }

    @Override
    public void damageEntity(Entity damager, Entity receiver) {
        this.damageEntity(receiver, damager.getDamage());
    }

    @Override
    public void damageEntity(Entity entity, int damage) {
        if (!entity.isInvulnerable()) {
            entity.removeHealth(damage);
            if (entity.isDeath()) {
                this.removeEntity(entity);
                return;
            }
            entity.getUpdaterManager().add(new EntityDamageAnimationUpdater());
        }
    }

    @Override
    public List<Particle> getParticles() {
        return new ArrayList<>(this.particles);
    }

    @Override
    public void addParticles(Image particle, Location location, int count, ParticuleUpdater updater, int lifetime) {
        this.addParticles(particle, Color.color(0, 0, 0), location, count, updater, lifetime);
    }

    @Override
    public void addParticles(Color particle, Location location, int count, ParticuleUpdater updater, int lifetime) {
        this.addParticles(null, particle, location, count, updater, lifetime);
    }

    private void addParticles(
        Image texture,
        Color color,
        Location location,
        int count,
        ParticuleUpdater updater,
        int lifetime
    ) {
        final double xRatio = (double) ZeldaLikeApplication.WIDTH / (double) this.getWidth();
        final double yRatio = (double) ZeldaLikeApplication.HEIGHT / (double) this.getHeight();
        for (int i = 0; i < count; i++) {
            final Particle currentParticle = new ImplParticle(
                this.zeldaLike,
                texture,
                color,
                ImplVector.create(
                    (float) ((location.getX() * xRatio) + (xRatio / 2.0)),
                    (float) ((location.getY() * yRatio)  + (yRatio / 2.0))
                ),
                lifetime
            );
            currentParticle.getUpdaterManager().add(updater.clone());
            this.particles.add(currentParticle);
        }
    }

    @Override
    public void removeParticle(Particle particle) {
        this.particles.remove(particle);
    }

    private <T> T getObjectAt(T[][] objects, int x, int y) {
        if (x < 0 || x >= objects.length || y < 0 || y >= objects[x].length) {
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

    private <T> T setObject(T[][] objects, Class<? extends T> clazz, Location location) {
        try {
            final T type = clazz
                .getConstructor(ZeldaLike.class, Location.class)
                .newInstance(this.zeldaLike, location);
            if (
                location.getX() > -1 && location.getX() < objects.length
                    && location.getY() > -1 && location.getY() < objects[location.getX()].length
            ) {
                objects[location.getX()][location.getY()] = type;
            }
            return type;
        } catch (Throwable throwable) {
            System.out.println("Error: " + location.getX() + ":" + location.getY() + " [" + objects.length + "|" + objects[location.getX()].length + "]");
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

    @Override
    public boolean isHUDShow() {
        return this.hudShow;
    }

    @Override
    public void setHUDShow(boolean hudShow) {
        this.hudShow = hudShow;
    }

    @Override
    public boolean isDebugShow() {
        return this.debugShow;
    }

    @Override
    public void setDebugShow(boolean debugShow) {
        this.debugShow = debugShow;
    }
}

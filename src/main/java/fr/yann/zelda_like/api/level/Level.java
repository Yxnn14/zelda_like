package fr.yann.zelda_like.api.level;

import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.dialog.DialogManager;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.particule.Particule;
import fr.yann.zelda_like.api.updater.ParticuleUpdater;
import fr.yann.zelda_like.api.updater.Updater;
import fr.yann.zelda_like.api.updater.UpdaterManager;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

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

    <T extends Block> T setBlock(Class<T> blockClass, Location location);

    List<Entity> getEntities();

    Entity getEntityAt(int x, int y);

    default Entity getEntityAt(Location location) {
        return this.getEntityAt(location.getX(), location.getY());
    }

    <T extends Entity> T spawn(Class<T> entityClazz, Location location);

    boolean moveEntity(Entity entity, Location location);

    void removeEntity(Entity entity);

    List<Particule> getParticules();

    void addParticules(Image particule, Location location, int count, ParticuleUpdater updater, int lifetime);
    void addParticules(Color particule, Location location, int count, ParticuleUpdater updater, int lifetime);

    void removeParticule(Particule particule);

    LevelGenerator getGenerator();

    DialogManager getDialogManager();

    UpdaterManager<Level> getUpdaterManager();

    boolean isPause();

    void setPause(boolean pause);

    boolean isHUDShow();

    void setHUDShow(boolean hudShow);

    boolean isDebugShow();

    void setDebugShow(boolean debugShow);
}

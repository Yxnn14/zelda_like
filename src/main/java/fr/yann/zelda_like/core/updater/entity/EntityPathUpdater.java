package fr.yann.zelda_like.core.updater.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.updater.Updater;
import fr.yann.zelda_like.core.updater.AbstractUpdater;

public class EntityPathUpdater extends AbstractUpdater<Entity> {

    private final Direction direction;
    private final int range;
    private boolean switchDirection;
    private int pathCounter;

    public EntityPathUpdater(Direction direction, int range, int speed) {
        super(speed);
        this.direction = direction;
        this.range = range;
    }

    @Override
    public void onUpdate(ZeldaLike zeldaLike, Entity entity) {
        final Level level = zeldaLike.getLevelManager().get();
        int x = 0;
        int y = 0;
        if (this.direction.equals(Direction.HORIZONTAL)) {
            x = this.switchDirection ? 1 : -1;
        }
        else {
            y = this.switchDirection ? 1 : -1;
        }
        int newPathCounter = this.pathCounter + x + y;
        if (Math.abs(newPathCounter) >= range) {
            this.switchDirection = !this.switchDirection;
            return;
        }
        if (!level.getBlockAt(entity.getLocation().getX() + x, entity.getLocation().getY() + y).isTransparent()) {
            this.switchDirection = !this.switchDirection;
            return;
        }
        if (level.getEntityAt(entity.getLocation().getX() + x, entity.getLocation().getY() + y) != null) {
            this.switchDirection = !this.switchDirection;
            return;
        }
        level.moveEntity(entity, entity.getLocation().add(x, y));
        this.pathCounter = newPathCounter;
    }

    public enum Direction {
        HORIZONTAL,
        VERTICAL
    }
}

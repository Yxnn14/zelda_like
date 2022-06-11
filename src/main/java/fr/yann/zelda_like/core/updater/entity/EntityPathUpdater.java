package fr.yann.zelda_like.core.updater.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.updater.Updater;

public class EntityPathUpdater implements Updater<Entity> {

    private final Direction direction;
    private final int range;
    private boolean switchDirection;
    private int pathCounter;
    private final int speed;
    private int nextMove;

    public EntityPathUpdater(Direction direction, int range, int speed) {
        this.direction = direction;
        this.range = range;
        this.speed = speed;
    }

    @Override
    public void update(ZeldaLike zeldaLike, Entity entity) {
        if (this.nextMove > 0) {
            this.nextMove --;
            return;
        }
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

        this.nextMove = this.speed;
    }

    public enum Direction {
        HORIZONTAL,
        VERTICAL
    }
}

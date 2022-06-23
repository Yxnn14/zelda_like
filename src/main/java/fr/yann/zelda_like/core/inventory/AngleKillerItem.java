package fr.yann.zelda_like.core.inventory;

import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.core.entity.AngleEntity;
import javafx.scene.paint.Color;

public class AngleKillerItem extends AbstractItem {
    public AngleKillerItem() {
        super("Angle Killer", Color.color(0.0, 0.0, 1.0));
    }

    @Override
    public boolean use(Entity entity) {
        int x = 0;
        int y = 0;
        switch (entity.getLocation().getOrientation()) {
            case NORTH -> y = -1;
            case SOUTH -> y = 1;
            case WEST -> x = -1;
            case EAST -> x = 1;
        }
        final Entity target = entity.getZeldaLike().getLevelManager().get().getEntityAt(entity.getLocation().add(x, y));
        if (target instanceof AngleEntity) {
            target.setInvulnerable(false);
            entity.getZeldaLike().getLevelManager().get().damageEntity(entity, target, target.getHealth());
            return true;
        }
        return false;
    }
}

package fr.yann.zelda_like.core.inventory;

import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.MonsterEntity;
import javafx.scene.paint.Color;

public class KillEntityItem extends AbstractItem {
    public KillEntityItem() {
        super("Killer Item", Color.color(0.5, 0.5, 0.5));
    }

    @Override
    public boolean use(Entity entity) {
        Entity target = null;
        double minDistance = -1;
        for (Entity currentEntity : entity.getZeldaLike().getLevelManager().get().getEntities(MonsterEntity.class)) {
            if (currentEntity.equals(entity)) {
                continue;
            }
            if (target == null) {
                target = currentEntity;
                minDistance = entity.getLocation().getDistance(currentEntity.getLocation());
                continue;
            }
            double distance = entity.getLocation().getDistance(currentEntity.getLocation());
            if (distance < minDistance) {
                target = currentEntity;
                minDistance = distance;
            }
        }
        if (target != null) {
            target.setInvulnerable(false);
            entity.getZeldaLike().getLevelManager().get().damageEntity(entity, target, target.getHealth());
            return true;
        }
        return false;
    }
}

package fr.yann.zelda_like.core.updater.animation;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.updater.ResetUpdater;
import fr.yann.zelda_like.core.updater.AbstractUpdater;

public class EntityDamageAnimationUpdater extends AbstractUpdater<Entity> implements ResetUpdater<Entity> {

    private int lifetime = 7;
    private Entity entity;

    public EntityDamageAnimationUpdater() {
        super(2);
    }

    @Override
    public void onUpdate(ZeldaLike zeldaLike, Entity entity) {
        if (this.entity == null) {
            this.entity = entity;
        }
        if (lifetime <= 0) {
            entity.setVisible(true);
            entity.getUpdaterManager().remove(this.getClass());
            return;
        }
        entity.setVisible(!entity.isVisible());
        this.lifetime--;
    }

    @Override
    public void reset() {
        this.lifetime = 7;
        if (this.entity != null) {
            this.entity.setVisible(true);
        }
    }
}

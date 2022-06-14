package fr.yann.zelda_like.core.entity;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.dialog.Dialog;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.VillagerEntity;
import fr.yann.zelda_like.api.level.Location;
import fr.yann.zelda_like.core.dialog.ImplDialog;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractVillagerEntity extends AbstractEntity implements VillagerEntity {

    private final List<Dialog> dialogs = new ArrayList<>();
    protected AbstractVillagerEntity(ZeldaLike zeldaLike, String name, Location location, Color color, int health) {
        super(zeldaLike, name, location, color, health);
    }

    @Override
    public boolean interact(Entity entity) {
        this.zeldaLike.getLevelManager().get().getDialogManager()
                .add(this.dialogs.toArray(new Dialog[0]));
        return true;
    }

    @Override
    public VillagerEntity addDialog(Dialog dialog) {
        this.dialogs.add(dialog);
        return this;
    }
}

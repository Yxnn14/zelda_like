package fr.yann.zelda_like.core.updater.block;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.block.Block;
import fr.yann.zelda_like.api.entity.Entity;
import fr.yann.zelda_like.api.entity.PlayerEntity;
import fr.yann.zelda_like.api.updater.Updater;
import fr.yann.zelda_like.core.dialog.ImplDialog;

public class DemoBlockUpdater implements Updater<Block> {
    private boolean send;

    @Override
    public void update(ZeldaLike zeldaLike, Block block) {
        final Entity entity = zeldaLike.getLevelManager().get().getEntityAt(block.getLocation());
        if (entity instanceof PlayerEntity) {
            if (!this.send) {
                this.send = true;
                zeldaLike.getLevelManager().get().getDialogManager()
                    .add(
                        ImplDialog.create("Bienvenue sur le block de démo"),
                        ImplDialog.create("Celui-ci est un test.")
                    );
            }
            return;
        }
        this.send = false;
    }
}

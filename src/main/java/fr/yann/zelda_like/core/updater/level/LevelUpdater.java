package fr.yann.zelda_like.core.updater.level;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.controller.Controller;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.updater.Updater;

public class LevelUpdater implements Updater<Level> {
    private boolean pressed = false;
    @Override
    public void update(ZeldaLike zeldaLike, Level level) {
        if (!level.isPause()) {
            level.getEntities().forEach(entity -> entity.getUpdaterManager().update());
            level.getBlocks().forEach(block -> block.getUpdaterManager().update());
            level.getParticles().forEach(particule -> particule.getUpdaterManager().update());
        }
        level.getDialogManager().getUpdaterManager().update();

        Controller controller = zeldaLike.getControllerManager().of(Controller.FUNCTION_1);
        if (controller != null && controller.isPressed()) {
            if (!this.pressed) {
                level.setHUDShow(!level.isHUDShow());
            }
            this.pressed = true;
            return;
        }

        controller = zeldaLike.getControllerManager().of(Controller.FUNCTION_2);
        if (controller != null && controller.isPressed()) {
            if (!this.pressed) {
                level.setDebugShow(!level.isDebugShow());
            }
            this.pressed = true;
            return;
        }
        this.pressed = false;
    }
}

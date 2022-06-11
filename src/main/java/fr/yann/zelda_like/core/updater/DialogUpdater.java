package fr.yann.zelda_like.core.updater;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.controller.Controller;
import fr.yann.zelda_like.api.dialog.Dialog;
import fr.yann.zelda_like.api.dialog.DialogManager;
import fr.yann.zelda_like.api.level.Level;
import fr.yann.zelda_like.api.updater.Updater;

public class DialogUpdater implements Updater<DialogManager> {

    private final int showTick;

    private int remainingShowTick;
    private boolean lastStatePause;
    private boolean checkStatePause = true;

    public DialogUpdater(int showTick) {
        this.showTick = showTick;
    }

    @Override
    public void update(ZeldaLike zeldaLike, DialogManager dialogManager) {
        final Dialog dialog = dialogManager.get();
        if (dialog == null && this.remainingShowTick > 0) {
            this.remainingShowTick = 0;
        }
        final Level level = zeldaLike.getLevelManager().get();
        if (this.remainingShowTick == 0) {
            if (dialogManager.next() != null) {
                this.remainingShowTick = this.showTick;
                if (this.checkStatePause) {
                    this.checkStatePause = false;
                    this.lastStatePause = zeldaLike.getLevelManager().get().isPause();
                }
                level.setPause(true);
            } else if (dialog != null) {
                level.setPause(this.lastStatePause);
                this.lastStatePause = false;
                this.checkStatePause = true;
            }
            return;
        }
        if (!level.isPause()) {
            this.lastStatePause = false;
            level.setPause(true);
        }
        final Controller controller = zeldaLike.getControllerManager().of(Controller.ACTION);
        if (controller != null && controller.isPressed()) {
            this.remainingShowTick = 0;
            return;
        }
        this.remainingShowTick--;
    }
}

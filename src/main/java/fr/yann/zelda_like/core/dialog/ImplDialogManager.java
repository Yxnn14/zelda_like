package fr.yann.zelda_like.core.dialog;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.dialog.Dialog;
import fr.yann.zelda_like.api.dialog.DialogManager;
import fr.yann.zelda_like.api.updater.UpdaterManager;
import fr.yann.zelda_like.core.updater.DialogUpdater;
import fr.yann.zelda_like.core.updater.ImplUpdaterManager;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

public class ImplDialogManager implements DialogManager {
    private final Queue<Dialog> dialogs = new ArrayDeque<>();
    private final UpdaterManager<DialogManager> updaterManager;
    private Dialog dialog;

    public ImplDialogManager(ZeldaLike zeldaLike, int showTick) {
        this.updaterManager = new ImplUpdaterManager<>(zeldaLike, this);
        this.updaterManager.add(new DialogUpdater(showTick));
    }

    @Override
    public Dialog get() {
        return this.dialog;
    }

    @Override
    public void add(Dialog... dialogs) {
        Collections.addAll(this.dialogs, dialogs);
    }

    @Override
    public Dialog next() {
        return this.dialog = this.dialogs.poll();
    }

    @Override
    public void clear() {
        this.dialogs.clear();
    }

    @Override
    public UpdaterManager<DialogManager> getUpdaterManager() {
        return this.updaterManager;
    }
}

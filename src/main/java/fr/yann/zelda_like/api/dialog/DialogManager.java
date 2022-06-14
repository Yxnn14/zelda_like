package fr.yann.zelda_like.api.dialog;

import fr.yann.zelda_like.api.updater.UpdaterManager;

public interface DialogManager {
    Dialog get();

    void add(Dialog... dialogs);

    Dialog next();

    void clear();

    UpdaterManager<DialogManager> getUpdaterManager();
}

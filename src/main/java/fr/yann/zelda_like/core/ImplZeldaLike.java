package fr.yann.zelda_like.core;

import fr.yann.zelda_like.api.ZeldaLike;
import fr.yann.zelda_like.api.controller.ControllerManager;
import fr.yann.zelda_like.api.dialog.DialogManager;
import fr.yann.zelda_like.api.level.LevelManager;
import fr.yann.zelda_like.core.controller.ImplControllerManager;
import fr.yann.zelda_like.core.level.ImplLevelManager;
import javafx.scene.Group;

public class ImplZeldaLike implements ZeldaLike {

    private final System.Logger logger;
    private final Group scene;
    private final LevelManager levelManager = new ImplLevelManager(this);
    private final ControllerManager controllerManager = new ImplControllerManager();

    public ImplZeldaLike(System.Logger logger, Group scene) {
        this.logger = logger;
        this.scene = scene;
    }

    @Override
    public System.Logger getLogger() {
        return this.logger;
    }

    @Override
    public Group getScene() {
        return this.scene;
    }

    @Override
    public LevelManager getLevelManager() {
        return this.levelManager;
    }

    @Override
    public ControllerManager getControllerManager() {
        return this.controllerManager;
    }
}

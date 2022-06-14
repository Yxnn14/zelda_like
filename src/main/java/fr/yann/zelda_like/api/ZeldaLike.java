package fr.yann.zelda_like.api;

import fr.yann.zelda_like.api.controller.ControllerManager;
import fr.yann.zelda_like.api.level.LevelManager;
import javafx.scene.Group;

public interface ZeldaLike {
    System.Logger getLogger();
    Group getScene();
    LevelManager getLevelManager();

    ControllerManager getControllerManager();
}

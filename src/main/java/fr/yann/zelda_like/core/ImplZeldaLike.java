package fr.yann.zelda_like.core;

import fr.yann.zelda_like.api.ZeldaLike;
import javafx.scene.Group;

public class ImplZeldaLike implements ZeldaLike {

    private final System.Logger logger;
    private final Group scene;

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
}

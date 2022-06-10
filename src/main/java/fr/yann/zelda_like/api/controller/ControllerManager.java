package fr.yann.zelda_like.api.controller;

import javafx.scene.input.KeyCode;

public interface ControllerManager {
    Controller of(String key);

    Controller by(KeyCode keyCode);

    ControllerManager register(Controller controller);
}

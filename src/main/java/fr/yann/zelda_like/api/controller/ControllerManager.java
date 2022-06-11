package fr.yann.zelda_like.api.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public interface ControllerManager {
    Controller of(String key);

    Controller by(KeyCode keyCode);

    Controller by(MouseButton button);

    ControllerManager register(Controller controller);

    Cursor getCursor();
}

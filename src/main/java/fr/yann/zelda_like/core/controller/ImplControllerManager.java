package fr.yann.zelda_like.core.controller;

import fr.yann.zelda_like.api.controller.Controller;
import fr.yann.zelda_like.api.controller.ControllerManager;
import fr.yann.zelda_like.api.controller.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.util.HashMap;
import java.util.Map;

public class ImplControllerManager implements ControllerManager {
    private final Map<String, Controller> controllerMap = new HashMap<>();
    private final Map<KeyCode, Controller> bindKeyControllerPrimary = new HashMap<>();
    private final Map<KeyCode, Controller> bindKeyControllerSecondary = new HashMap<>();
    private final Map<MouseButton, Controller> bindMouseController = new HashMap<>();

    private final Cursor cursor = new ImplCursor();

    @Override
    public Controller of(String key) {
        return this.controllerMap.get(key);
    }

    @Override
    public Controller by(KeyCode keyCode) {
        Controller controller = this.bindKeyControllerPrimary.get(keyCode);
        if (controller == null) {
            controller = this.bindKeyControllerSecondary.get(keyCode);
        }
        return controller;
    }

    @Override
    public Controller by(MouseButton button) {
        return this.bindMouseController.get(button);
    }

    @Override
    public ControllerManager register(Controller controller) {
        this.controllerMap.put(controller.getKey(), controller);
        if (controller.getKeyCodePrimary() != null) {
            this.bindKeyControllerPrimary.put(controller.getKeyCodePrimary(), controller);
        }
        if (controller.getKeyCodeSecondary() != null) {
            this.bindKeyControllerSecondary.put(controller.getKeyCodeSecondary(), controller);
        }
        if (controller.getButton() != null) {
            this.bindMouseController.put(controller.getButton(), controller);
        }
        return this;
    }

    @Override
    public Cursor getCursor() {
        return this.cursor;
    }
}

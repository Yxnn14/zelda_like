package fr.yann.zelda_like.core.controller;

import fr.yann.zelda_like.api.controller.Controller;
import fr.yann.zelda_like.api.controller.ControllerManager;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class ImplControllerManager implements ControllerManager {
    private final Map<String, Controller> controllerMap = new HashMap<>();
    private final Map<KeyCode, Controller> bindControllerPrimary = new HashMap<>();
    private final Map<KeyCode, Controller> bindControllerSecondary = new HashMap<>();

    @Override
    public Controller of(String key) {
        return this.controllerMap.get(key);
    }

    @Override
    public Controller by(KeyCode keyCode) {
        Controller controller = this.bindControllerPrimary.get(keyCode);
        if (controller == null) {
            controller = this.bindControllerSecondary.get(keyCode);
        }
        return controller;
    }

    @Override
    public ControllerManager register(Controller controller) {
        this.controllerMap.put(controller.getKey(), controller);
        this.bindControllerPrimary.put(controller.getKeyCodePrimary(), controller);
        if (controller.getKeyCodeSecondary() != null) {
            this.bindControllerSecondary.put(controller.getKeyCodeSecondary(), controller);
        }
        return this;
    }
}

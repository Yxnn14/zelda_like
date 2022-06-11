package fr.yann.zelda_like.core.controller;

import fr.yann.zelda_like.api.controller.Controller;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import java.util.Objects;

public class ImplController implements Controller {

    public static Controller create(String key, KeyCode keyCodePrimary) {
        return ImplController.create(key, keyCodePrimary, null, null);
    }

    public static Controller create(String key, KeyCode keyCodePrimary, KeyCode keyCodeSecondary) {
        return ImplController.create(key, keyCodePrimary, keyCodeSecondary, null);
    }

    public static Controller create(String key, MouseButton button) {
        return ImplController.create(key, null, null, button);
    }

    public static Controller create(String key, KeyCode keyCodePrimary, MouseButton button) {
        return ImplController.create(key, keyCodePrimary, null, button);
    }

    public static Controller create(String key, KeyCode keyCodePrimary, KeyCode keyCodeSecondary, MouseButton button) {
        return new ImplController(key, keyCodePrimary, keyCodeSecondary, button);
    }

    private final String key;
    private final KeyCode keyCodePrimary;
    private final KeyCode keyCodeSecondary;
    private final MouseButton button;
    private boolean pressed;

    private ImplController(String key, KeyCode keyCodePrimary, KeyCode keyCodeSecondary, MouseButton button) {
        this.key = key;
        this.keyCodePrimary = keyCodePrimary;
        this.keyCodeSecondary = keyCodeSecondary;
        this.button = button;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public KeyCode getKeyCodePrimary() {
        return this.keyCodePrimary;
    }

    @Override
    public KeyCode getKeyCodeSecondary() {
        return this.keyCodeSecondary;
    }

    @Override
    public MouseButton getButton() {
        return this.button;
    }

    @Override
    public boolean isPressed() {
        return this.pressed;
    }

    @Override
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}

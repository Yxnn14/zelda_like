package fr.yann.zelda_like.core.controller;

import fr.yann.zelda_like.api.controller.Controller;
import javafx.scene.input.KeyCode;

import java.util.Objects;

public class ImplController implements Controller {

    public static Controller create(String key, KeyCode keyCodePrimary) {
        return ImplController.create(key, keyCodePrimary, null);
    }

    public static Controller create(String key, KeyCode keyCodePrimary, KeyCode keyCodeSecondary) {
        return new ImplController(key, keyCodePrimary, keyCodeSecondary);
    }

    private final String key;
    private final KeyCode keyCodePrimary;
    private final KeyCode keyCodeSecondary;
    private boolean pressed;

    private ImplController(String key, KeyCode keyCodePrimary, KeyCode keyCodeSecondary) {
        this.key = key;
        this.keyCodePrimary = Objects.requireNonNull(keyCodePrimary);
        this.keyCodeSecondary = keyCodeSecondary;
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
    public boolean isPressed() {
        return this.pressed;
    }

    @Override
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }
}

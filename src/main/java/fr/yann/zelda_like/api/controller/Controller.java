package fr.yann.zelda_like.api.controller;

import javafx.scene.input.KeyCode;

public interface Controller
{
    public static final String UP = "UP";
    public static final String DOWN = "DOWN";
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";
    public static final String ACTION = "ACTION";
    public static final String OPEN_INVENTORY = "OPEN_INVENTORY";

    String getKey();

    KeyCode getKeyCodePrimary();

    KeyCode getKeyCodeSecondary();

    boolean isPressed();

    void setPressed(boolean pressed);
}

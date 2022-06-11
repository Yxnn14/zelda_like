package fr.yann.zelda_like.api.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public interface Controller
{
    public static final String UP = "UP";
    public static final String DOWN = "DOWN";
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";
    public static final String ACTION = "ACTION";
    public static final String OPEN_INVENTORY = "OPEN_INVENTORY";


    public static final String SLOT_1 = "SLOT_1";
    public static final String SLOT_2 = "SLOT_2";
    public static final String SLOT_3 = "SLOT_3";
    public static final String SLOT_4 = "SLOT_4";
    public static final String SLOT_5 = "SLOT_5";
    public static final String SLOT_6 = "SLOT_6";
    public static final String SLOT_7 = "SLOT_7";
    public static final String SLOT_8 = "SLOT_8";
    public static final String SLOT_9 = "SLOT_9";
    public static final String SLOT_10 = "SLOT_10";



    String getKey();

    KeyCode getKeyCodePrimary();

    KeyCode getKeyCodeSecondary();

    MouseButton getButton();

    boolean isPressed();

    void setPressed(boolean pressed);
}

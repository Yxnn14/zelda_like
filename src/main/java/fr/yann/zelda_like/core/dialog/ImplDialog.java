package fr.yann.zelda_like.core.dialog;

import fr.yann.zelda_like.api.dialog.Dialog;
import javafx.scene.paint.Color;

public class ImplDialog implements Dialog {

    public static Dialog create(String text) {
        return ImplDialog.create(text, Color.color(1, 1, 1));
    }

    public static Dialog create(String text, Color color) {
        return new ImplDialog(text, color);
    }

    private final String text;
    private final Color color;

    private ImplDialog(String text, Color color) {
        this.text = text;
        this.color = color;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public Color getColor() {
        return this.color;
    }
}

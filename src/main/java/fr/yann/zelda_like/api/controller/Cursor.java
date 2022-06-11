package fr.yann.zelda_like.api.controller;

public interface Cursor {
    double getX();

    void setX(double x);

    double getY();

    void setY(double y);

    void move(double x, double y);

    boolean isDragged();

    void setDragged(boolean dragged);

    double getDragX();

    void setDragX(double x);

    double getDragY();

    void setDragY(double y);

    void moveDrag(double x, double y);
}

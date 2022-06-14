package fr.yann.zelda_like.core.controller;

import fr.yann.zelda_like.api.controller.Cursor;

public class ImplCursor implements Cursor {

    private double x;
    private double y;

    private boolean dragged;
    private double dragX;
    private double dragY;

    protected ImplCursor() {}

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void move(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isDragged() {
        return this.dragged;
    }

    @Override
    public void setDragged(boolean dragged) {
        this.dragged = dragged;
    }

    @Override
    public double getDragX() {
        return this.dragX;
    }

    @Override
    public void setDragX(double x) {
        this.dragX = x;
    }

    @Override
    public double getDragY() {
        return this.dragY;
    }

    @Override
    public void setDragY(double y) {
        this.dragY = y;
    }

    @Override
    public void moveDrag(double x, double y) {
        this.dragX = x;
        this.dragY = y;
    }
}

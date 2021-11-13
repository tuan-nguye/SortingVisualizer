package com.sorting.javafx.sortable;

import javafx.scene.shape.Shape;

public interface Transitionable {
    Shape getShape();
    double getX();
    void setX(double x);
    double getY();
    void setY(double y);
    double getWidth();
    double getHeight();
    void setHeight(double height);
}

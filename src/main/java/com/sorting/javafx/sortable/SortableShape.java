package com.sorting.javafx.sortable;

import javafx.animation.Transition;
import javafx.scene.shape.Shape;
import com.sorting.javafx.sortable.Transitionable;

public abstract class SortableShape implements Sortable, Transitionable {
    private int number;

    public SortableShape(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    public abstract Transition scaleY(int toNum, int ms);
    public abstract Transition setToDefault();
}

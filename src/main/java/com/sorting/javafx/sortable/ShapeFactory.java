package com.sorting.javafx.sortable;

import com.sorting.util.SortableArrayWrapper;
import javafx.scene.shape.Shape;

public abstract class ShapeFactory {
    private int max;
    private double width, heightFactor;

    public ShapeFactory(int max, double windowWidth, double windowHeight) {
        this.max = max;
        this.width = calculateWidth(max, windowWidth);
        this.heightFactor = calculateHeightFactor(max, windowHeight);
    }

    public double calculateOffset(int offset) {
        return offset * width;
    }

    public double calculateWidth(int max, double windowWidth) {
        return windowWidth/max;
    }

    public double calculateHeightFactor(int max, double windowHeight) {
        return windowHeight/max;
    }

    public double getWidth() {
        return width;
    }

    public double getHeightFactor() {
        return heightFactor;
    }

    public int getMax() {
        return this.max;
    }

    public abstract SortableShape createFormattedShape(int num, int offset);
}

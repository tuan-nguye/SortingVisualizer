package com.sorting.javafx.sortable.bar;

import com.sorting.javafx.sortable.ShapeFactory;
import javafx.scene.paint.Color;

public class PointFactory extends ShapeFactory {
    public PointFactory(int max, double windowWidth, double windowHeight) {
        super(max, windowWidth, windowHeight);
    }

    @Override
    public Point createFormattedShape(int num, int offset) {
        Point point = new Point(num, this.getWidth(), this.getHeightFactor());
        point.setX(calculateOffset(offset));
        point.setY((num-1)*getHeightFactor());
        point.getShape().setFill(Color.WHITE);

        return point;
    }
}

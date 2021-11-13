package com.sorting.javafx.sortable.bar;

import com.sorting.javafx.sortable.ShapeFactory;
import com.sorting.javafx.sortable.SortableShape;
import com.sorting.util.SortableArrayWrapper;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BarFactory extends ShapeFactory {
    public BarFactory(int max, double windowWidth, double windowHeight) {
        super(max, windowWidth, windowHeight);
    }

    @Override
    public Bar createFormattedShape(int num, int offset) {
        Bar bar = new Bar(num, this.getWidth(), num*this.getHeightFactor());
        bar.setX(calculateOffset(offset));
        bar.getShape().setFill(Color.WHITE);
        bar.getShape().setStroke(Color.BLACK);

        return bar;
    }
}

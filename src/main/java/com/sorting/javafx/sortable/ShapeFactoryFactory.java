package com.sorting.javafx.sortable;

import com.sorting.javafx.sortable.bar.BarFactory;
import com.sorting.javafx.sortable.bar.PointFactory;

public class ShapeFactoryFactory {
    public static ShapeFactory getFactory(int max, double windowWidth, double windowHeight, String style) {
        switch(style) {
            case "Bar":
                return new BarFactory(max, windowWidth, windowHeight);

            case "Point":
                return new PointFactory(max, windowWidth, windowHeight);

            default:
                return new PointFactory(max, windowWidth, windowHeight);
        }
    }
}

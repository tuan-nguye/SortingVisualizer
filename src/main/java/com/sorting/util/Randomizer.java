package com.sorting.util;

import com.sorting.javafx.sortable.ShapeFactory;
import com.sorting.javafx.sortable.ShapeFactoryFactory;
import com.sorting.javafx.sortable.SortableShape;
import javafx.animation.SequentialTransition;
import javafx.scene.shape.Shape;

import java.util.*;


public class Randomizer {
    public static SortableArrayWrapper randomizeInRange(ShapeFactory shapeFactory) {
        List<Integer> availableNumbers = listAllNumbers(shapeFactory.getMax());
        SortableArrayWrapper unsortedArray = new SortableArrayWrapper(shapeFactory.getMax());

        int arrayIndex = 0;
        while(!availableNumbers.isEmpty()) {
            int randomIndex = (int) (Math.random() * availableNumbers.size());
            int num = availableNumbers.remove(randomIndex);
            SortableShape shape = shapeFactory.createFormattedShape(num, arrayIndex);

            unsortedArray.set(arrayIndex++, shape);
        }

        return unsortedArray;
    }

    private static List<Integer> listAllNumbers(int n) {
        List<Integer> allNumbers = new ArrayList<>();

        for(int i = 1; i <= n; i++)
            allNumbers.add(i);

        return allNumbers;
    }

    public static SequentialTransition shuffle(SortableArrayWrapper array) {
        if(array == null) return null;

        SequentialTransition sequential = new SequentialTransition();
        //SortableArrayWrapper tempArray = randomizeInRange(array.length(), )
        return null;
    }

    public static void main(String[] args) {
        ShapeFactory shapeFactory = ShapeFactoryFactory.getFactory(20, 100, 100, "Bar");
        SortableArrayWrapper array = Randomizer.randomizeInRange(shapeFactory);
        for(SortableShape shape : array.toArray())
            System.out.printf("n = %d\n", shape.getNumber());
    }
}

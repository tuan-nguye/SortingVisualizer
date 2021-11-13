package com.sorting.util;

import com.sorting.algorithms.Sort;
import com.sorting.javafx.sortable.ShapeFactoryFactory;
import com.sorting.javafx.sortable.Sortable;
import com.sorting.javafx.sortable.SortableShape;
import javafx.animation.Transition;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class SortableArrayWrapper {
    private SortableShape[] dataElements;
    private TransitionUtils transitionUtils;
    private Sort algorithm;

    public SortableArrayWrapper(int size) {
        this.dataElements = new SortableShape[size];
        this.transitionUtils = new TransitionUtils();
    }

    public SortableArrayWrapper(SortableArrayWrapper original) {
        setArray(original.toArray());
    }

    public void setAlgorithm(Sort algorithm) {
        this.algorithm = algorithm;
    }


    public Transition swap(int i, int j, int ms) {
        return transitionUtils.swap(dataElements, i, j, ms);
    }
    public Transition move(int i, int distance, int ms) {
        return transitionUtils.move(dataElements, i, distance, ms);
    }
    public Transition insertAndShift(int indexO, int index, int ms) {
        return transitionUtils.insertAndShift(dataElements, indexO, index, ms);
    }
    public Transition setNumber(int index, int number, int ms) {
        return transitionUtils.setNumber(dataElements, index, number, ms);
    }
    public Transition reset() {
        return transitionUtils.reset(dataElements);
    }

    public SortableShape[] toArray() {
        return Arrays.copyOf(dataElements, dataElements.length);
    }

    public int[] toIntArray() {
        int[] nums = new int[size()];

        for(int i = 0; i < size(); i++)
            nums[i] = dataElements[i].getNumber();

        return nums;
    }

    public SortableShape get(int index) {
        return dataElements[index];
    }

    public void set(int index, SortableShape t) {
        dataElements[index] = t;
    }

    public int size() {
        return this.dataElements.length;
    }

    public void setArray(SortableShape[] array) {
        this.dataElements = Arrays.copyOf(array, array.length, SortableShape[].class);
    }

    public Transition sort() {
        if(algorithm == null) throw new IllegalStateException("no sorting algorithm chosen");
        return algorithm.sort(this);
    }

    public Iterator<SortableShape> iterator() {
        Iterator<SortableShape> it = new Iterator<>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < dataElements.length;
            }

            @Override
            public SortableShape next() {
                if(!hasNext()) throw new NoSuchElementException();
                return dataElements[index++];
            }
        };

        return it;
    }

    public static void main(String[] args) {
        SortableArrayWrapper array = Randomizer.randomizeInRange(ShapeFactoryFactory.getFactory(20, 100, 100, "Bar"));
        for(SortableShape ss : array.toArray())
            System.out.print(ss.getNumber() + ", ");

        array.reset();
        for(Iterator<SortableShape> it = array.iterator(); it.hasNext();) {
            SortableShape ss = it.next();
            System.out.printf("\n(height, number, y) = (%f, %d, %f)", ss.getHeight(), ss.getNumber(), ss.getY());
        }
    }
}

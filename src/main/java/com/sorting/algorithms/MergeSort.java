package com.sorting.algorithms;

import com.sorting.javafx.sortable.bar.Bar;
import com.sorting.util.*;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;

public class MergeSort implements Sort {
    private SequentialTransition mergeSortAnimation;

    public MergeSort() {
        mergeSortAnimation = new SequentialTransition();
    }

    @Override
    public Transition sort(SortableArrayWrapper array) {
        mergeSortAnimation = new SequentialTransition();

        mergeSort(array, 0, array.size());
        return mergeSortAnimation;
    }

    private void mergeSort(SortableArrayWrapper array, int left, int right) {
        //printBar(array);
        if(Math.abs(left-right) <= 1) return;

        int middle = (left+right)/2;

        // left side
        mergeSort(array, left, middle);
        // right side
        mergeSort(array, middle, right);

        zip(array, left, middle, right);
    }

    // final version that only works on original array and no extra space
    private void zip(SortableArrayWrapper array, int left, int middle, int right) {
        int leftArrayLimit = middle, rightArrayLimit = right;
        int indexO = left;

        while((left != leftArrayLimit) && (middle != rightArrayLimit)) {
            if(array.get(left).getNumber() < array.get(middle).getNumber()) {
                indexO++;
                left++;
            } else {
                Transition parallel = array.insertAndShift(indexO, middle, 15);
                mergeSortAnimation.getChildren().add(parallel);

                indexO++;
                left++;
                leftArrayLimit++;
                middle++;
            }
        }
    }

    /* prototype that uses temporary array
    private <T extends SortableShape> void zip2(SortableArrayWrapper<T> array, int left, int middle, int right) {
        int indexO = left, indexL = 0, indexR = middle-left;
        int leftArrayLength = middle-left, rightArrayLength = right-left;
        T[] temp = Arrays.copyOfRange(array.toArray(), left, right);


        while((indexL != leftArrayLength) && (indexR != rightArrayLength)) {
            if(temp[indexL].getNumber() < temp[indexR].getNumber())
                array.set(indexO++, temp[indexL++]);
            else {
                array.set(indexO++, temp[indexR++]);
            }
        }

        while(indexL != leftArrayLength) array.set(indexO++, temp[indexL++]);
        while(indexR != rightArrayLength) array.set(indexO++, temp[indexR++]);
    }
    */


    void printBar(Bar[] array) {
        for(Bar b : array)
            System.out.print(b.getNumber() + ", ");
        System.out.print("\n");
    }
}

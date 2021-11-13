package com.sorting.algorithms;

import com.sorting.javafx.sortable.bar.Bar;
import com.sorting.util.*;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;

public class QuickSort implements Sort {
    private SequentialTransition quickSortAnimation;

    public QuickSort() {
        this.quickSortAnimation = new SequentialTransition();
    }

    @Override
    public Transition sort(SortableArrayWrapper array) {
        this.quickSortAnimation = new SequentialTransition();
        return quickSort(array, 0, array.size()-1);
    }

    private SequentialTransition quickSort(SortableArrayWrapper array, int low, int high) {
        int partition = partition(array, low, high);

        if(low < partition) quickSort(array, low, partition-1);
        if(partition < high) quickSort(array, partition+1, high);

        return this.quickSortAnimation;
    }

    private int partition(SortableArrayWrapper array, int low, int high) {
        int pivot = (low+high)/2;
        int pivotValue = array.get(pivot).getNumber();

        while(low < high) {
            while(array.get(low).getNumber() < pivotValue) low++;
            while(array.get(high).getNumber() > pivotValue) high--;

            if(low < high)
                quickSortAnimation.getChildren().add(array.swap(low, high, 25));

        }

        return low;
    }

    public void printArray(Bar[] array) {
        StringBuilder out = new StringBuilder("");
        out.append("[");
        for(Bar bar : array) {
            out.append(bar.getNumber() + ", ");
        }
        out.append("]");

        System.out.println(out);
    }
}

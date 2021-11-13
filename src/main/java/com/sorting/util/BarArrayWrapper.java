/*
package com.sorting.util;

import com.sorting.javafx.sortable.bar.Bar;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;

public class BarArrayWrapper extends SortableArrayWrapper<Bar> {

    private BarTransitionUtils barTransitionUtils;

    public BarArrayWrapper(int size) {
        super(size);
        this.barTransitionUtils = new BarTransitionUtils();
    }

    @Override
    public ParallelTransition swap(int i, int j, int ms) {
        return barTransitionUtils.swap(this, i, j, ms);
    }

    @Override
    public TranslateTransition move(int i, int distance, int ms) {
        return barTransitionUtils.move(this, i, distance, ms);
    }

    @Override
    public ParallelTransition insertAndShift(int indexO, int index, int ms) {
        return barTransitionUtils.insertAndShift(this, indexO, index, ms);
    }

    @Override
    public Bar[] toArray() {
        Bar[] array = new Bar[this.length()];

        for(int i = 0; i < this.length(); i++)
            array[i] = this.get(i);

        return array;
    }
}


 */
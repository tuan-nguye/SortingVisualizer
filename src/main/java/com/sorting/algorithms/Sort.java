package com.sorting.algorithms;

import com.sorting.javafx.sortable.SortableShape;
import com.sorting.util.SortableArrayWrapper;
import javafx.animation.Transition;

public interface Sort {
    Transition sort(SortableArrayWrapper array);
}

package com.sorting.algorithms;

import com.sorting.util.*;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort implements Sort {

    public BubbleSort() {}

    @Override
    public Transition sort(SortableArrayWrapper array) {
        SequentialTransition sort = new SequentialTransition();
        List<Transition> transitions = new ArrayList<>();

        for(int i = 0; i < array.size()-1; i++) {
            for(int j = 0; j < array.size()-i-1; j++) {
                if(array.get(j).getNumber() > array.get(j+1).getNumber()) {
                    Transition swap = array.swap(j, j+1, 1);
                    transitions.add(swap);
                }
            }
        }

        sort.getChildren().addAll(transitions);
        return sort;
    }
}

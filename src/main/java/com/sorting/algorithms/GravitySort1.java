package com.sorting.algorithms;

import com.sorting.javafx.sortable.ShapeFactoryFactory;
import com.sorting.javafx.sortable.SortableShape;
import com.sorting.util.Randomizer;
import com.sorting.util.SortableArrayWrapper;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GravitySort1 implements Sort {
    @Override
    public Transition sort(SortableArrayWrapper array) {
        SequentialTransition seq = new SequentialTransition();
        List<Transition> transitions = new ArrayList<>();

        for(int i = 0; i < array.size(); i++) {
            transitions.add(shift(array, i));
        }

        seq.getChildren().addAll(transitions);
        return seq;
    }

    private ParallelTransition shift(SortableArrayWrapper array, int max) {
        ParallelTransition shift = new ParallelTransition();

        int smol = array.size()-1;
        for(int j = array.size()-1; j > max; j--) {
            if(array.get(j-1).getNumber() > array.get(smol).getNumber()) continue;

            shift.getChildren().add(array.insertAndShift(j, smol, 20));
            smol = j-1;
        }

        if(array.get(max).getNumber() > array.get(smol).getNumber())
            shift.getChildren().add(array.insertAndShift(max, smol, 20));

        return shift;
    }

    public static void main(String[] args) {
        SortableArrayWrapper unsorted = Randomizer.randomizeInRange(ShapeFactoryFactory.getFactory(20, 100, 100, "Bar"));
        GravitySort1 sort = new GravitySort1();

        for(Iterator<SortableShape> it = unsorted.iterator(); it.hasNext();) {
            System.out.print(it.next().getNumber() + ", ");
        }
        System.out.print("\n");

        sort.shift(unsorted, 0);

        for(Iterator<SortableShape> it = unsorted.iterator(); it.hasNext();) {
            System.out.print(it.next().getNumber() + ", ");
        }
        System.out.print("\n");
    }

}

package com.sorting.algorithms;

import com.sorting.javafx.sortable.ShapeFactoryFactory;
import com.sorting.javafx.sortable.SortableShape;
import com.sorting.util.Randomizer;
import com.sorting.util.SortableArrayWrapper;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;

import java.util.*;

public class GravitySort2 implements Sort {
    @Override
    public Transition sort(SortableArrayWrapper array) {
        int largestElement = largestElement(array);
        BitSet[] beads = initializeBeads(largestElement, array.size());
        int[] indices = new int[largestElement];
        Arrays.fill(indices, array.size()-1);

        int[] nums = array.toIntArray();
        SequentialTransition seq = new SequentialTransition();
        seq.getChildren().add(array.reset());

        seq.getChildren().add(insertBeads(array, nums, beads, indices));
        return seq;
    }

    private int largestElement(SortableArrayWrapper array) {
        int largest = 0;

        for(Iterator<SortableShape> it = array.iterator(); it.hasNext();) {
            int current = it.next().getNumber();
            if(current > largest) largest = current;
        }

        return largest;
    }

    private BitSet[] initializeBeads(int x, int y) {
        BitSet[] beads = new BitSet[x];

        for(int i = 0; i < beads.length; i++)
            beads[i] = new BitSet(y);

        return beads;
    }

    private Transition insertBeads(SortableArrayWrapper array, int[] nums, BitSet[] beads, int[] indices) {
        List<Transition> transitions = new ArrayList<>();

        for(int i = 0; i < nums.length; i++) {
            for(int k = 0; k < nums[i]; k++)
                beads[k].set(indices[k]--);

            transitions.add(updateView(array, beads, i));
        }

        SequentialTransition seq = new SequentialTransition();
        seq.getChildren().addAll(transitions);
        return seq;
    }

    private Transition updateView(SortableArrayWrapper array, BitSet[] beads, int limit) {
        List<Transition> transitions = new ArrayList<>();

        for(int i = array.size()-1; i >= array.size()-1-limit; i--) {
            int newValue = count(beads, i);
            transitions.add(array.setNumber(i, newValue, 30));
        }

        ParallelTransition parallel = new ParallelTransition();
        parallel.getChildren().addAll(transitions);
        return parallel;
    }

    private int[] count(BitSet[] beads) {
        List<Integer> count = new ArrayList<>();

        for(int i = beads[0].length()-1; i >= 0; i--) {
            int num = 0;

            for(int j = 0; j < beads.length; j++) {
                if(beads[j].get(i)) num++;
                else break;
            }

            if(num == 0) break;
            count.add(num);
        }

        int[] result = count.stream().mapToInt(i -> i).toArray();

        return result;
    }

    private int count(BitSet[] beads, int column) {
        int num = 0;

        for(int i = 0; i < beads.length; i++) {
            if(beads[i].get(column)) num++;
            else break;
        }

        return num;
    }

    // DEPRECATED
    private Transition correctGravitySort(SortableArrayWrapper array) {
        SortableArrayWrapper result = new SortableArrayWrapper(array.size());
        int[] beads = new int[largestElement(array)];
        insertBeads(array, beads);
        int[] sorted = fallInPlace(beads);


        return gravitySort(array, sorted);
    }
    // DEPRECATED
    private ParallelTransition gravitySort(SortableArrayWrapper array, int[] sorted) {
        ParallelTransition parallel = new ParallelTransition();

        for(int i = 0; i < array.size(); i++) {
            for(int j = 0; j < sorted.length; j++) {
                if(array.get(i).getNumber() == sorted[j])
                    parallel.getChildren().add(array.move(i, j-i, 1000));
            }
        }

        return parallel;
    }
    // DEPRECATED
    private void insertBeads(SortableArrayWrapper original, int[] beads) {
        for(int i = 0; i < original.size(); i++) {
            int num = original.get(i).getNumber();

            for(int j = 0; j < num; j++)
                beads[j] = beads[j] + 1;
        }
    }

    // DEPRECATED
    private int[] fallInPlace(int[] beads) {
        int[] array = new int[beads[0]];

        for(int i = 0; i < array.length; i++) {
            int num = 0;
            for(int j = 0; j < beads.length; j++) {
                if(beads[j] > 0) {
                    num++;
                    beads[j] = beads[j] - 1;
                }
            }
            array[(array.length-1)-i] = num;
        }

        return array;
    }

    public static void main(String[] args) {
/*
        GravitySort2 sort = new GravitySort2();
        SortableArrayWrapper unsorted = Randomizer.randomizeInRange(ShapeFactoryFactory.getFactory(10000, 100, 100, "Bar"));
        for(Iterator<SortableShape> it = unsorted.iterator(); it.hasNext();) {
            System.out.print(it.next().getNumber() + ", ");
        }
        System.out.print("\n");

        sort.sort(unsorted);

 */

        GravitySort2 sort = new GravitySort2();
        SortableArrayWrapper unsorted = Randomizer.randomizeInRange(ShapeFactoryFactory.getFactory(10, 100, 100, "Bar"));
        for(Iterator<SortableShape> it = unsorted.iterator(); it.hasNext();) {
            System.out.print(it.next().getNumber() + ", ");
        }
        System.out.print("\n");

        BitSet[] beads = sort.initializeBeads(10, 10);
        int[] indices = new int[10];
        Arrays.fill(indices, 9);
        int[] unSorted = unsorted.toIntArray();
        sort.insertBeads(unsorted, unSorted, beads, indices);

        for(int i = 0; i < 10; i++) {
            System.out.printf("%d: %d\n", i, sort.count(beads, i));
        }



    }
}

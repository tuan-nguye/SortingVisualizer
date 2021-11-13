package com.sorting.util;


import com.sorting.javafx.sortable.SortableShape;
import com.sorting.javafx.sortable.bar.Bar;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class TransitionUtils {
    public TransitionUtils() {}

    /***
     * swaps the bar elements at position i and j, and updates their position inside the javafx.control.Pane
     * @param array
     * @param i: index of first element
     * @param j: index of second element
     * @return returns the animation for the transition
     */
    public Transition swap(SortableShape[] array, int i, int j, int ms) {
        SortableShape temp = array[i];
        int xDifference = i-j;

        Transition moveI = move(array, i, -xDifference, ms);
        Transition moveJ = move(array, j, xDifference, ms);

        ParallelTransition swap = new ParallelTransition(moveI, moveJ);

        array[i] = array[j];
        array[j] = temp;

        return swap;
    }

    public Transition move(SortableShape[] array, int i, int distance, int ms) {
        TranslateTransition move = new TranslateTransition(Duration.ONE, array[i].getShape());
        move.setByX(array[i].getWidth()*distance);
        move.setDelay(Duration.millis(ms));

        return move;
    }

    public Transition insertAndShift(SortableShape[] array, int indexO, int index, int ms) {
        ParallelTransition parallel = new ParallelTransition();
        SortableShape temp = array[index];

        if(indexO < index) parallel.getChildren().add(shiftRight(array, indexO, index, ms));
        else parallel.getChildren().add(shiftLeft(array,indexO, index, ms));

        array[indexO] = temp;
        TranslateTransition translate = new TranslateTransition(Duration.ONE, temp.getShape());
        translate.setByX((indexO-index)*temp.getWidth());
        translate.setDelay(Duration.millis(ms));
        parallel.getChildren().add(translate);

        return parallel;
    }

    private Transition shiftRight(SortableShape[] array, int indexO, int index, int ms) {
        ParallelTransition parallel = new ParallelTransition();

        for(int i = index; i > indexO; i--) {
            array[i] = array[i-1];
            Transition shift = move(array, i-1, 1, ms);
            parallel.getChildren().add(shift);
        }

        return parallel;
    }

    private Transition shiftLeft(SortableShape[] array, int indexO, int index, int ms) {
        ParallelTransition parallel = new ParallelTransition();

        for(int i = index; i < indexO; i++) {
            array[i] = array[i+1];
            Transition shift = move(array, i+1, -1, ms);
            parallel.getChildren().add(shift);
        }

        return parallel;
    }

    public Transition setNumber(SortableShape[] array, int index, int number, int ms) {
        SortableShape ss = array[index];
        /*
        int scale = number - ss.getNumber();
         */
        Transition transition = ss.scaleY(number, ms);
        ss.setNumber(number);
        return transition;
    }

    public Transition reset(SortableShape[] array) {
        List<Transition> tr = new ArrayList<>();

        for(SortableShape ss : array)
            tr.add(ss.setToDefault());

        ParallelTransition parallel = new ParallelTransition();
        parallel.getChildren().addAll(tr);

        return parallel;
    }

    public FillTransition markSelectedBars(Bar[] array, int i, Paint paint) {
        return null;
    }

    public static void main(String[] args) {
        int i = 4, j = 3;
        double test = i/((double) j);
        System.out.println(test);
    }
}
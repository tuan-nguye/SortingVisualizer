package com.sorting.javafx;

import com.sorting.algorithms.*;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmChoiceBox {
    public static ChoiceBox setupChoiceBox(ChoiceBox choiceBox) {
        choiceBox.setItems(FXCollections.observableList(getAllAlgorithms()));
        choiceBox.setConverter(getStringConverter());

        return choiceBox;
    }

    private static List<Sort> getAllAlgorithms() {
        List<Sort> allAlgorithms = new ArrayList<>();

        allAlgorithms.add(new BogoSort());
        allAlgorithms.add(new BubbleSort());
        allAlgorithms.add(new GravitySort1());
        allAlgorithms.add(new GravitySort2());
        allAlgorithms.add(new HeapSort());
        allAlgorithms.add(new InsertionSort());
        allAlgorithms.add(new MergeSort());
        allAlgorithms.add(new PigeonholeSort());
        allAlgorithms.add(new QuickSort());
        allAlgorithms.add(new RadixSort());

        return allAlgorithms;
    }

    private static StringConverter<Sort> getStringConverter() {
        StringConverter<Sort> converter = new StringConverter<Sort>() {
            @Override
            public String toString(Sort sort) {
                if(sort == null) return null;
                else
                    return sort.getClass().getSimpleName();
            }

            @Override
            public Sort fromString(String str) {
                Sort sort = null;
                try {
                    sort = (Sort) Class.forName(str).getConstructor().newInstance();
                } catch(Exception e) {}

                return sort;
            }
        };

        return converter;
    }
}

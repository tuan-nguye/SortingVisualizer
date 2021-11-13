package com.sorting.javafx;

import com.sorting.algorithms.Sort;
import com.sorting.javafx.sortable.ShapeFactory;
import com.sorting.javafx.sortable.ShapeFactoryFactory;
import com.sorting.javafx.sortable.SortableShape;
import com.sorting.javafx.sortable.bar.Bar;
import com.sorting.javafx.sortable.bar.Point;
import com.sorting.util.Randomizer;
import com.sorting.util.SortableArrayWrapper;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

import java.util.Iterator;

public class SortingController {
    @FXML private Pane arrayPane;
    @FXML private TextField rangeTextField;
    @FXML private ChoiceBox chooseAlgorithm;
    @FXML private ToggleButton pointBtn, barBtn;
    private SortableArrayWrapper arrayWrapper;
    private String viewType;

    public SortingController() {}

    @FXML
    public void initialize() {
        rangeTextField.setTextFormatter(new TextFormatter(new IntegerStringConverter()));
        rangeTextField.setText("150");
        chooseAlgorithm = AlgorithmChoiceBox.setupChoiceBox(chooseAlgorithm);
        pointBtn.fire();
        /*

        ShapeFactory barFactory = ShapeFactoryFactory.getFactory(10, 600, 300, "Point");
        Point p = (Point) barFactory.createFormattedShape(5, 5);
        System.out.printf("stroke width: %f, scale: %f, height: %f\n", p.getShape().getStrokeWidth(), p.getShape().getScaleY(), p.getHeight());
        SequentialTransition seq = new SequentialTransition(p.setToDefault());
        arrayPane.getChildren().add(p.getShape());


        Transition tr = p.scaleY(10, 1000);
        tr.setOnFinished(e -> {
            System.out.printf("stroke width: %f, scale: %f\n", p.getShape().getStrokeWidth(), p.getShape().getScaleY());
        });

        seq.getChildren().add(tr);


        seq.play();

         */

        Rectangle rec = new Rectangle(300, 100, 50, 50);
        rec.setFill(Color.RED);
        arrayPane.getChildren().add(rec);

        TranslateTransition translate1 = new TranslateTransition(Duration.millis(2000), rec);
        translate1.setToY(200);

        TranslateTransition translate2 = new TranslateTransition(Duration.millis(2000), rec);
        translate2.setToX(200);

        TranslateTransition translate3 = new TranslateTransition(Duration.millis(2000), rec);
        translate3.setToY(0);
        translate3.setToX(0);

        SequentialTransition seq = new SequentialTransition(translate1, translate2, translate3);
        seq.play();


    }

    @FXML
    public void randomize() {
        int range = (int) rangeTextField.getTextFormatter().getValue();
        arrayPane.getChildren().clear();
        arrayWrapper = Randomizer.randomizeInRange(ShapeFactoryFactory.getFactory(range, arrayPane.getWidth(), arrayPane.getHeight(), viewType));

        Iterator<SortableShape> it = arrayWrapper.iterator();
        while(it.hasNext()) {
            SortableShape sortableShape = it.next();
            arrayPane.getChildren().add(sortableShape.getShape());
        }
    }

    @FXML
    public void togglePoint() {
        viewType = "Point";
        pointBtn.setTextFill(Color.GREEN);
        barBtn.setSelected(false);
        barBtn.setTextFill(Color.RED);
    }

    @FXML
    public void toggleBar() {
        viewType = "Bar";
        barBtn.setTextFill(Color.GREEN);
        pointBtn.setSelected(false);
        pointBtn.setTextFill(Color.RED);
    }

    @FXML
    public void sort() {
        arrayWrapper.setAlgorithm((Sort) chooseAlgorithm.getSelectionModel().getSelectedItem());
        Transition sortAnimation;

        try {
            sortAnimation = arrayWrapper.sort();
        } catch(IllegalStateException ise) {
            sortAnimation = new SequentialTransition();
        }

        sortAnimation.play();
    }
}

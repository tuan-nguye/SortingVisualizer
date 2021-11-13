package com.sorting.javafx.sortable.bar;

import com.sorting.javafx.sortable.SortableShape;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

public class Bar extends SortableShape {
    private Rectangle rec;

    public Bar(int n, double width, double height) {
        super(n);
        this.rec = new Rectangle(width, height);
    }

    public Transition scaleY(int toNum, int ms) {
        ScaleTransition scale = new ScaleTransition(Duration.millis(ms), rec);
        scale.setToY(toNum);
        rec.setStrokeWidth(1.0/toNum);

        TranslateTransition translate = new TranslateTransition(Duration.millis(ms), rec);
        translate.setByY(rec.getHeight()*(toNum-this.getNumber())*0.5);

        ParallelTransition parallel = new ParallelTransition(scale, translate);

        return parallel;
    }

    @Override
    public Transition setToDefault() {
        double height = this.getHeight()/this.getNumber();
        rec.setHeight(height);

        this.setNumber(1);
        Transition tr = scaleY(0, 1);
        this.setNumber(0);

        return tr;
    }

    @Override
    public Shape getShape() {
        return rec;
    }

    @Override
    public double getX() {
        return rec.getX();
    }

    @Override
    public void setX(double x) {
        rec.setX(x);
    }

    @Override
    public double getY() {
        return rec.getY();
    }

    @Override
    public void setY(double y) {
        rec.setY(y);
    }

    @Override
    public double getWidth() {
        return rec.getWidth();
    }

    @Override
    public double getHeight() {
        return rec.getHeight();
    }

    @Override
    public void setHeight(double height) {
        rec.setHeight(height);
    }
}

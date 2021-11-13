package com.sorting.javafx.sortable.bar;

import com.sorting.javafx.sortable.SortableShape;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Point extends SortableShape {
    private Rectangle rec;

    public Point(int n, double width, double height) {
        super(n);
        this.rec = new Rectangle(width, height);
    }

    @Override
    public Transition scaleY(int toNum, int ms) {
        TranslateTransition translate = new TranslateTransition(Duration.millis(ms), rec);
        translate.setByY((toNum-this.getNumber())*this.getHeight());

        translate.setOnFinished(e -> {
           if(rec.getY() < 0) rec.setVisible(false);
           else rec.setVisible(true);
        });

        return translate;
    }

    @Override
    public Transition setToDefault() {
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

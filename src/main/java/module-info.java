module com.sorting {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sortingvisualizer to javafx.fxml;
    exports com.sortingvisualizer;

    opens com.sorting.algorithms to javafx.fxml;
    exports com.sorting.algorithms;

    opens com.sorting.javafx to javafx.fxml;
    exports com.sorting.javafx;

    opens com.sorting.util to javafxfxml;
    exports com.sorting.util;
    exports com.sorting;
    opens com.sorting to javafx.fxml;
    exports com.sorting.javafx.sortable;
    opens com.sorting.javafx.sortable to javafx.fxml;
    exports com.sorting.javafx.sortable.bar;
    opens com.sorting.javafx.sortable.bar to javafx.fxml;
}
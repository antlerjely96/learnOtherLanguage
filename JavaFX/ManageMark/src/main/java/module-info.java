module managemark.managemark {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens managemark.managemark to javafx.fxml;
    exports managemark.managemark;
    exports managemark.managemark.Controller;
    opens managemark.managemark.Controller to javafx.fxml;
}
module se233.camelot {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires org.slf4j;
    requires javafx.swing ;

    opens se233.camelot to javafx.fxml;
    opens se233.camelot.controller to javafx.fxml;

    exports se233.camelot;
    exports se233.camelot.controller;
}
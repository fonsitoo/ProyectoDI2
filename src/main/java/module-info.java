module edu.arf.liceo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens edu.arf.liceo to javafx.fxml;
    opens edu.arf.liceo.controller to javafx.fxml;
    opens edu.arf.liceo.model to javafx.base;

    exports edu.arf.liceo;
}
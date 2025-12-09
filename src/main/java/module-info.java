module edu.arf.liceo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    opens edu.arf.liceo to javafx.fxml;

    exports edu.arf.liceo;


}
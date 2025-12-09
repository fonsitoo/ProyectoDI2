package edu.arf.liceo.controller;

import edu.arf.liceo.dao.ItemDAO;
import edu.arf.liceo.model.Item;
import edu.arf.liceo.model.Usuario;
import edu.arf.liceo.utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class SellController {
    @FXML private TextField txtNombre;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtPrecio;
    @FXML private Slider sliderFloat;
    @FXML private Label lblFloatValue;
    @FXML private CheckBox chkStattrak;
    @FXML private RadioButton rbPublico;
    @FXML private RadioButton rbAmigos;
    @FXML private ToggleGroup visibilidadGroup;

    @FXML
    public void initialize() {
        sliderFloat.valueProperty().addListener((obs, oldVal, newVal) -> {
            lblFloatValue.setText(String.format("%.4f", newVal));
        });
    }
    @FXML
    public void onGuardarClick() {
        System.out.println("Botón guardar pulsado. Valor slider: " + sliderFloat.getValue());
    }
    @FXML
    public void onCancelarClick() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        SceneSwitcher.switchScene("/market.fxml", "Steam Community Market", stage);
    }
}
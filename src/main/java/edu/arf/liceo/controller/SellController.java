package edu.arf.liceo.controller;

import edu.arf.liceo.dao.ItemDAO;
import edu.arf.liceo.model.Item;
import edu.arf.liceo.utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class SellController {

    @FXML private TextField txtNombre;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtPrecio;
    @FXML private Label lblComision;

    @FXML private Slider sliderFloat;
    @FXML private Label lblFloatValue;
    @FXML private Label lblWearName;

    @FXML private CheckBox chkStattrak;

    @FXML private RadioButton rbPublico;
    @FXML private RadioButton rbAmigos;
    @FXML private ToggleGroup visibilidadGroup;

    @FXML
    public void initialize() {
        sliderFloat.valueProperty().addListener((obs, oldVal, newVal) -> {
            double valor = newVal.doubleValue();
            lblFloatValue.setText(String.format("%.4f", valor));
            lblWearName.setText(calcularNombreDesgaste(valor));
        });

        txtPrecio.textProperty().addListener((obs, oldVal, newVal) -> {
            calcularGanancia(newVal);
        });
    }

    private String calcularNombreDesgaste(double val) {
        if (val < 0.07) return "Recién Fabricado (Factory New)";
        if (val < 0.15) return "Casi Nuevo (Minimal Wear)";
        if (val < 0.38) return "Algo Desgastado (Field-Tested)";
        if (val < 0.45) return "Bastante Desgastado (Well-Worn)";
        return "Deplorable (Battle-Scarred)";
    }

    private void calcularGanancia(String textoPrecio) {
        try {
            double precio = Double.parseDouble(textoPrecio);
            double comision = precio * 0.15;
            double ganancia = precio - comision;
            lblComision.setText(String.format("Recibirás: %.2f€ (Steam: -%.2f€)", ganancia, comision));
        } catch (NumberFormatException e) {
            lblComision.setText("Introduce un número válido");
        }
    }

    @FXML
    public void onGuardarClick() {
        if (txtNombre.getText().isEmpty() || txtPrecio.getText().isEmpty()) {
            mostrarAlerta("Error", "Faltan datos", "El nombre y el precio son obligatorios.");
            return;
        }

        try {
            Item item = new Item();
            item.setNombre(txtNombre.getText() + (chkStattrak.isSelected() ? " (StatTrak™)" : ""));
            item.setDescripcion(txtDescripcion.getText());
            item.setPrecio(Double.parseDouble(txtPrecio.getText()));
            item.setFloatValue(sliderFloat.getValue());
            item.setFechaPublicacion(LocalDate.now());

            item.setIdUsuario(2);
            item.setIdJuego(1);

            ItemDAO dao = new ItemDAO();
            boolean exito = dao.insertarItem(item);

            if (exito) {
                mostrarAlerta("Éxito", "Item publicado", "Tu item está ahora en el mercado.");
                volverAlMercado();
            } else {
                mostrarAlerta("Error", "Fallo al guardar", "No se pudo conectar con la base de datos.");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Precio inválido", "El precio debe ser un número (usa punto para decimales).");
        }
    }

    @FXML
    public void onCancelarClick() {
        volverAlMercado();
    }

    private void volverAlMercado() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        SceneSwitcher.switchScene("/market.fxml", "Steam Community Market", stage);
    }

    private void mostrarAlerta(String titulo, String header, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
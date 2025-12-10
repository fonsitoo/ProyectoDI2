package edu.arf.liceo.controller;

import edu.arf.liceo.dao.CategoriaDAO;
import edu.arf.liceo.dao.ItemDAO;
import edu.arf.liceo.model.Categoria;
import edu.arf.liceo.model.Item;
import edu.arf.liceo.utils.SceneSwitcher;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

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

    @FXML private ListView<Categoria> lstCategorias;

    private CategoriaDAO categoriaDAO;

    @FXML
    public void initialize() {
        categoriaDAO = new CategoriaDAO();

        sliderFloat.valueProperty().addListener((obs, oldVal, newVal) -> {
            double valor = newVal.doubleValue();
            lblFloatValue.setText(String.format("%.4f", valor));
            lblWearName.setText(calcularNombreDesgaste(valor));
        });

        txtPrecio.textProperty().addListener((obs, oldVal, newVal) -> {
            calcularGanancia(newVal);
        });

        lstCategorias.getItems().addAll(categoriaDAO.listarTodas());
        lstCategorias.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private String calcularNombreDesgaste(double val) {
        if (val < 0.07) return "Recién Fabricado (FN)";
        if (val < 0.15) return "Casi Nuevo (MW)";
        if (val < 0.38) return "Algo Desgastado (FT)";
        if (val < 0.45) return "Bastante Desgastado (WW)";
        return "Deplorable (BS)";
    }

    private void calcularGanancia(String textoPrecio) {
        try {
            if (textoPrecio.isEmpty()) return;
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
            mostrarAlerta("Error", "Faltan datos", "Nombre y precio obligatorios.");
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

            ItemDAO itemDAO = new ItemDAO();
            boolean exito = itemDAO.insertarItem(item);

            if (exito) {
                int idNuevoItem = categoriaDAO.obtenerUltimoIdItem();

                List<Categoria> categoriasSeleccionadas = lstCategorias.getSelectionModel().getSelectedItems();

                if (!categoriasSeleccionadas.isEmpty()) {
                    categoriaDAO.guardarRelacionCategorias(idNuevoItem, categoriasSeleccionadas);
                }

                mostrarAlerta("Éxito", "Item publicado", "Tu item y sus categorías se han guardado.");
                volverAlMercado();
            } else {
                mostrarAlerta("Error", "Fallo BD", "No se pudo guardar el item.");
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Precio inválido", "Revisa el precio.");
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
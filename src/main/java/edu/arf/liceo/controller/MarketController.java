package edu.arf.liceo.controller;

import edu.arf.liceo.dao.ItemDAO;
import edu.arf.liceo.model.Item;
import edu.arf.liceo.model.Usuario;
import edu.arf.liceo.utils.SceneSwitcher;
import edu.arf.liceo.utils.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {

    @FXML private TableView<Item> tableItems;
    @FXML private TableColumn<Item, String> colNombre;
    @FXML private TableColumn<Item, Double> colPrecio;
    @FXML private TableColumn<Item, Double> colFloat;
    @FXML private TableColumn<Item, String> colDesc;

    @FXML private Button btnLogout;
    @FXML private Button btnEliminar;

    @FXML private Label lblUsuarioActivo;

    private ItemDAO itemDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemDAO = new ItemDAO();
        configurarTabla();
        cargarDatos();

        configurarVistaSegunUsuario();
    }

    private void configurarVistaSegunUsuario() {
        Usuario usuario = UserSession.getInstance().getUsuario();

        if (usuario != null) {
            String rol = usuario.isEsAdmin() ? "ADMIN" : "USER";
            lblUsuarioActivo.setText("Usuario: " + usuario.getNickname() + " | Rol: " + rol);

            if (usuario.isEsAdmin()) {
                if (btnEliminar != null) {
                    btnEliminar.setVisible(true);
                    btnEliminar.setManaged(true);
                }
            } else {
                if (btnEliminar != null) {
                    btnEliminar.setVisible(false);
                    btnEliminar.setManaged(false);
                }
            }
        } else {
            lblUsuarioActivo.setText("Error: Sin Sesión");
            if (btnEliminar != null) btnEliminar.setVisible(false);
        }
    }

    private void configurarTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colFloat.setCellValueFactory(new PropertyValueFactory<>("floatValue"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    }

    private void cargarDatos() {
        List<Item> listaItems = itemDAO.listarItems();
        ObservableList<Item> datosTabla = FXCollections.observableArrayList(listaItems);
        tableItems.setItems(datosTabla);
    }

    @FXML
    public void onLogoutClick() {
        UserSession.getInstance().cleanUserSession();
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        SceneSwitcher.switchScene("/login.fxml", "Steam Market Login", stage);
    }

    @FXML
    public void onVenderClick() {
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        SceneSwitcher.switchScene("/sell_item.fxml", "Vender Item", stage);
    }

    @FXML
    public void onEliminarClick() {
        Item itemSeleccionado = tableItems.getSelectionModel().getSelectedItem();
        if (itemSeleccionado == null) {
            mostrarAlerta("Error", "Nada seleccionado", "Selecciona un item para eliminar.");
            return;
        }

        boolean borrado = itemDAO.eliminarItem(itemSeleccionado.getIdItem());
        if (borrado) {
            tableItems.getItems().remove(itemSeleccionado);
            mostrarAlerta("Éxito", "Eliminado", "Item borrado correctamente.");
        } else {
            mostrarAlerta("Error", "Fallo", "No se pudo borrar.");
        }
    }

    private void mostrarAlerta(String titulo, String header, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
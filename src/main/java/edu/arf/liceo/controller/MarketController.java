package edu.arf.liceo.controller;

import edu.arf.liceo.dao.ItemDAO;
import edu.arf.liceo.model.Item;
import edu.arf.liceo.utils.SceneSwitcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {

    @FXML
    private TableView<Item> tableItems;
    @FXML
    private TableColumn<Item, String> colNombre;
    @FXML
    private TableColumn<Item, Double> colPrecio;
    @FXML
    private TableColumn<Item, Double> colFloat;
    @FXML
    private TableColumn<Item, String> colDesc;
    @FXML
    private Button btnLogout;

    private ItemDAO itemDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemDAO = new ItemDAO();
        configurarTabla();
        cargarDatos();
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
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        SceneSwitcher.switchScene("/login.fxml", "Steam Market Login", stage);
    }
}
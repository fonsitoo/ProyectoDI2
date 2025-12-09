package edu.arf.liceo.controller;

import edu.arf.liceo.dao.UsuarioDAO;
import edu.arf.liceo.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtNickname;

    @FXML
    private PasswordField txtPassword;

    public void onLoginClick() {
        String nick = txtNickname.getText();
        String pass = txtPassword.getText();

        if (nick.isEmpty() || pass.isEmpty()) {
            mostrarAlerta("Error al iniciar sesióo", "Han quedado campos vacios", "Por favor, rellena todos los campos.");
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.validarLogin(nick, pass);

        if (usuario != null) {
            mostrarAlerta("Éxito", "Bienvenido " + usuario.getNickname(), "Accediendo al mercado");

            javafx.stage.Stage stage = (javafx.stage.Stage) txtNickname.getScene().getWindow();
            edu.arf.liceo.utils.SceneSwitcher.switchScene("/market.fxml", "Mercado de la comunidad de Steam", stage);
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
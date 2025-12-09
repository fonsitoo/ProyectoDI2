package edu.arf.liceo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblMensaje;

    @FXML
    protected void onLoginButtonClick() {
        String usuario = txtUsuario.getText();
        String pass = txtPassword.getText();

        if (usuario.isEmpty() || pass.isEmpty()) {
            lblMensaje.setText("Por favor, rellena todos los campos.");
        } else {
            lblMensaje.setText("Intentando entrar como: " + usuario);
            System.out.println("Login clicado: " + usuario);
        }
    }
}
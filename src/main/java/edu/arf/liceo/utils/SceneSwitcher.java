package edu.arf.liceo.utils;

import edu.arf.liceo.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSwitcher {

    public static void switchScene(String fxmlFile, String title, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(fxmlFile));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            if (currentStage != null) {
                currentStage.setScene(scene);
                currentStage.setTitle(title);
                currentStage.centerOnScreen();
                currentStage.show();
            }

        } catch (IOException e) {
            System.err.println("Error al cargar la vista: " + fxmlFile);
            e.printStackTrace();
        }
    }
}
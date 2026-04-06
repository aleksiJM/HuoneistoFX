package fi.jyu.ohj2.aleksi.huoneisto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button lisaaAsuntoPainike;

    @FXML
    private Button muokkaaAsuntoaPainike;

    @FXML
    private Button poistaAsuntoPainike;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lisaaAsuntoPainike.setOnAction(actionEvent -> lisaaAsunto());
        muokkaaAsuntoaPainike.setOnAction(actionEvent -> muokkaaAsuntoa());
        poistaAsuntoPainike.setOnAction(actionEvent -> poistaAsunto());
    }

    private void lisaaAsunto() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fi/jyu/ohj2/aleksi/huoneisto/lisaa-asunto.fxml")
            );
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException error) {
            System.err.println("Virhe ladattaessa 'Lisää Asunto' näkymää");
            error.printStackTrace();
        }
    }

    private void muokkaaAsuntoa() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fi/jyu/ohj2/aleksi/huoneisto/muokkaa.fxml")
            );
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException error) {
            System.err.println("Virhe ladattaessa 'Muokkaa' näkymää");
            error.printStackTrace();
        }
    }

    private void poistaAsunto() {

    }
}
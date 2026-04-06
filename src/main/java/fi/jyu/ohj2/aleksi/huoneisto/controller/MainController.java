package fi.jyu.ohj2.aleksi.huoneisto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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

    }

    private void muokkaaAsuntoa() {

    }

    private void poistaAsunto() {

    }
}
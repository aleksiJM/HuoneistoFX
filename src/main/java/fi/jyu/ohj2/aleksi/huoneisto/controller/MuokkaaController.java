package fi.jyu.ohj2.aleksi.huoneisto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MuokkaaController implements Initializable {
    @FXML
    public Button lisaaAsukasPainike;

    @FXML
    public Button poistaAsukasPainike;

    @FXML
    public Button suljeMuokkausPainike;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lisaaAsukasPainike.setOnAction(actionEvent -> lisaaAsukas());
        poistaAsukasPainike.setOnAction(actionEvent -> poistaAsukas());
        suljeMuokkausPainike.setOnAction(actionEvent -> suljeMuokkaus());
    }

    private void lisaaAsukas() {
    }

    private void poistaAsukas() {
    }

    private void suljeMuokkaus() {
    }
}

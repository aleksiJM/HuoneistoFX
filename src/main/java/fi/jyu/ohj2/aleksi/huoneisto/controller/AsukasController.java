package fi.jyu.ohj2.aleksi.huoneisto.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AsukasController implements Initializable {
    @FXML
    private TextField asukkaanNimi;

    @FXML
    private TextField asukkaanIka;

    @FXML
    private TextField asukkaanYhteystiedot;

    @FXML
    private Button vahvistaAsukasPainike;

    @FXML
    private Button suljeAsukasPainike;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vahvistaAsukasPainike.setOnAction(actionEvent -> vahvistaAsukas());
        suljeAsukasPainike.setOnAction(actionEvent -> suljeAsukas());
    }

    private void vahvistaAsukas() {
    }

    private void suljeAsukas() {
        Stage stage = (Stage) suljeAsukasPainike.getScene().getWindow();
        stage.close();
    }
}

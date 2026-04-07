package fi.jyu.ohj2.aleksi.huoneisto.controller;

import fi.jyu.ohj2.aleksi.huoneisto.model.Asukas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
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

    private Asukas asukas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vahvistaAsukasPainike.setOnAction(actionEvent -> vahvistaAsukas());
        suljeAsukasPainike.setOnAction(actionEvent -> suljeAsukas());
    }

    private void vahvistaAsukas() {
        if (asukkaanNimi.getText().isEmpty()
                || asukkaanIka.getText().isEmpty()
                || asukkaanYhteystiedot.getText().isEmpty()) {
            return;
        }

        int ika;

        try {
            ika = Integer.parseInt(asukkaanIka.getText());
        } catch (NumberFormatException e) {
            System.out.println("Ikä pitää olla numero!");
            return;
        }

        asukas = new Asukas(
                asukkaanNimi.getText(),
                ika,
                asukkaanYhteystiedot.getText()
        );

        Stage stage = (Stage) vahvistaAsukasPainike.getScene().getWindow();
        stage.close();
    }

    private void suljeAsukas() {
        Stage stage = (Stage) suljeAsukasPainike.getScene().getWindow();
        stage.close();
    }

    public Asukas getAsukas() {
        return asukas;
    }
}

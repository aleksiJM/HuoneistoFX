package fi.jyu.ohj2.aleksi.huoneisto.controller;

import fi.jyu.ohj2.aleksi.huoneisto.model.Asukas;
import fi.jyu.ohj2.aleksi.huoneisto.model.Asunto;
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

    private Asunto muokattavaAsunto;

    private Asukas asukas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vahvistaAsukasPainike.setOnAction(actionEvent -> vahvistaAsukas());
        suljeAsukasPainike.setOnAction(actionEvent -> suljeAsukas());
        asukkaanNimi.setOnAction(actionEvent -> vahvistaAsukas());
        asukkaanIka.setOnAction(actionEvent -> vahvistaAsukas());
        asukkaanYhteystiedot.setOnAction(actionEvent -> vahvistaAsukas());
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
        muokattavaAsunto.lisaaAsukas(asukas);
        asukkaanNimi.clear();
        asukkaanIka.clear();
        asukkaanYhteystiedot.clear();
        asukkaanNimi.requestFocus();
    }

    private void suljeAsukas() {
        Stage stage = (Stage) suljeAsukasPainike.getScene().getWindow();
        stage.close();
    }

    public void setAsunto(Asunto asunto) {
        this.muokattavaAsunto = asunto;
    }
}

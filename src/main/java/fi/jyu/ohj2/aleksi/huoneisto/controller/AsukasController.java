package fi.jyu.ohj2.aleksi.huoneisto.controller;

import fi.jyu.ohj2.aleksi.huoneisto.model.Asukas;
import fi.jyu.ohj2.aleksi.huoneisto.model.Asunto;
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

    private Asunto muokattavaAsunto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vahvistaAsukasPainike.setOnAction(_ -> vahvistaAsukas());
        suljeAsukasPainike.setOnAction(_ -> suljeAsukas());
        asukkaanNimi.setOnAction(_ -> nimiPainike());
        asukkaanIka.setOnAction(_ -> ikaPainike());
        asukkaanYhteystiedot.setOnAction(_ -> yhteystiedotPainike());
    }

    private void nimiPainike() {
        String nimi = asukkaanNimi.getText().trim();
        if (nimi.isEmpty()) { return; }
        if (nimi.length() < 2) {
            asukkaanNimi.clear();
            asukkaanNimi.setPromptText("Nimi on liian lyhyt");
            return;
        }
        // nimen regex validointi
        if (!nimi.matches("^[a-zA-ZåäöÅÄÖ ]+$")) {
            asukkaanNimi.clear();
            asukkaanNimi.setPromptText("Nimi saa sisältää vain kirjaimia");
            return;
        }

        if (asukkaanIka.getText().isEmpty()) {
            asukkaanIka.requestFocus();
        } else if (asukkaanYhteystiedot.getText().isEmpty()) {
            asukkaanYhteystiedot.requestFocus();
        } else {
            vahvistaAsukas();
        }
    }

    private void ikaPainike() {
        try {
            int ika = Integer.parseInt(asukkaanIka.getText().trim());
            if (!(1 <= ika && ika <= 122)) {
                asukkaanIka.clear();
                asukkaanIka.setPromptText("Virheellinen ikä");
                return;
            }
        } catch (NumberFormatException eiNumero) {
            asukkaanIka.clear();
            asukkaanIka.setPromptText("Asukkaan ikä on oltava numero");
            return;
        }

        if (asukkaanIka.getText().isEmpty()) { return; }
        if (asukkaanNimi.getText().isEmpty()) {
            asukkaanNimi.requestFocus();
        } else if (asukkaanYhteystiedot.getText().isEmpty()) {
            asukkaanYhteystiedot.requestFocus();
        } else {
            vahvistaAsukas();
        }
    }

    private void yhteystiedotPainike() {
        String yhteystiedot = asukkaanYhteystiedot.getText().trim();

        // yhteystiedot regex (tekoälyn generoima)
        String emailRegex = "^[\\p{L}0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String puhelinRegex = "^\\+?\\d{7,15}$";

        if (!yhteystiedot.matches(emailRegex) && !yhteystiedot.matches(puhelinRegex)) {
            asukkaanYhteystiedot.clear();
            asukkaanYhteystiedot.setPromptText("Virheellinen sähköposti tai puhelinnumero");
            return;
        }

        if (asukkaanYhteystiedot.getText().isEmpty()) { return; }
        if (asukkaanNimi.getText().isEmpty()) {
            asukkaanNimi.requestFocus();
        } else if (asukkaanIka.getText().isEmpty()) {
            asukkaanIka.requestFocus();
        } else {
            vahvistaAsukas();
        }
    }

    private void vahvistaAsukas() {
        asukkaanNimi.setPromptText("");
        asukkaanIka.setPromptText("");
        asukkaanYhteystiedot.setPromptText("");

        String nimi = asukkaanNimi.getText().trim();
        String yhteystiedot = asukkaanYhteystiedot.getText().trim();

        int ika;
        try {
            ika = Integer.parseInt(asukkaanIka.getText().trim());
            if (!(1 <= ika && ika <= 122)) {
                asukkaanIka.clear();
                asukkaanIka.setPromptText("Virheellinen ikä");
                return;
            }
        } catch (NumberFormatException eiNumero) {
            asukkaanIka.clear();
            asukkaanIka.setPromptText("Asukkaan ikä on oltava numero");
            return;
        }

        if (nimi.length() < 2) {
            asukkaanNimi.clear();
            asukkaanNimi.setPromptText("Nimi on liian lyhyt");
            return;
        } else if (!nimi.matches("^[a-zA-ZåäöÅÄÖ ]+$")) {
            asukkaanNimi.clear();
            asukkaanNimi.setPromptText("Nimi saa sisältää vain kirjaimia");
            return;
        }

        String emailRegex = "^[\\p{L}0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String puhelinRegex = "^\\+?\\d{7,15}$";

        if (!yhteystiedot.matches(emailRegex) && !yhteystiedot.matches(puhelinRegex)) {
            asukkaanYhteystiedot.clear();
            asukkaanYhteystiedot.setPromptText("Virheellinen sähköposti tai puhelinnumero");
            return;
        }

        IO.println(nimi.length());
        Asukas asukas = new Asukas(muotoileNimi(nimi), ika, yhteystiedot);

        muokattavaAsunto.getAsukkaatObservable().add(asukas);
        asukkaanNimi.clear();
        asukkaanIka.clear();
        asukkaanYhteystiedot.clear();
        asukkaanNimi.requestFocus();
    }

    private String muotoileNimi(String nimi) {
        String[] osat = nimi.toLowerCase().split(" ");
        StringBuilder tulos = new StringBuilder();

        for (String osa : osat) {
            if (osa.isEmpty()) { continue; }

            tulos.append(osa.substring(0, 1)
                    .toUpperCase())
                    .append(osa.substring(1))
                    .append(" ");
        }

        return tulos.toString().trim();
    }

    private void suljeAsukas() {
        Stage stage = (Stage) suljeAsukasPainike.getScene().getWindow();
        stage.close();
    }

    public void setAsunto(Asunto asunto) {
        this.muokattavaAsunto = asunto;
    }
}

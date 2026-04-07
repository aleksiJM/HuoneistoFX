package fi.jyu.ohj2.aleksi.huoneisto.controller;

import fi.jyu.ohj2.aleksi.huoneisto.model.Asunto;
import fi.jyu.ohj2.aleksi.huoneisto.model.Taloyhtio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AsuntoController implements Initializable {
    @FXML
    private TextField asunnonTunnus;

    @FXML
    private Button vahvistaAsuntoPainike;

    @FXML
    private Button suljeAsuntoPainike;

    private Taloyhtio taloyhtio = new Taloyhtio();

    private Asunto asunto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vahvistaAsuntoPainike.setOnAction(actionEvent -> vahvistaAsunto());
        suljeAsuntoPainike.setOnAction(actionEvent -> suljeAsunto());
        asunnonTunnus.setOnAction(actionEvent -> vahvistaAsunto());
    }

    private void vahvistaAsunto() {
        String tunnus = asunnonTunnus.getText();
        if (Objects.equals(tunnus, "")) {
            return;
        }

        taloyhtio.lisaaAsunto(new Asunto(asunnonTunnus.getText()));
        asunnonTunnus.clear();
        asunnonTunnus.requestFocus();
    }

    private void suljeAsunto() {
        Stage stage = (Stage) suljeAsuntoPainike.getScene().getWindow();
        stage.close();
    }

    public Asunto getAsunto() {
        return asunto;
    }

    public void setTaloyhtio(Taloyhtio taloyhtio) {
        this.taloyhtio = taloyhtio;
    }
}

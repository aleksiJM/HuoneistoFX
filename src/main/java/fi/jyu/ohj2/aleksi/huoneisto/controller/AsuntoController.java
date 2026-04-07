package fi.jyu.ohj2.aleksi.huoneisto.controller;

import fi.jyu.ohj2.aleksi.huoneisto.model.Asunto;
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

    private Asunto asunto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vahvistaAsuntoPainike.setOnAction(actionEvent -> vahvistaAsunto());
        suljeAsuntoPainike.setOnAction(actionEvent -> suljeAsunto());
    }

    private void vahvistaAsunto() {
        if (Objects.equals(asunnonTunnus.getText(), "")) {
            return;
        }

        asunto = new Asunto(asunnonTunnus.getText());

        Stage stage = (Stage) vahvistaAsuntoPainike.getScene().getWindow();
        stage.close();
    }

    private void suljeAsunto() {
        Stage stage = (Stage) suljeAsuntoPainike.getScene().getWindow();
        stage.close();
    }

    public Asunto getAsunto() {
        return asunto;
    }
}

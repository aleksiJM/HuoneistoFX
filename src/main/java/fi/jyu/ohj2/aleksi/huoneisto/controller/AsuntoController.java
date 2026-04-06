package fi.jyu.ohj2.aleksi.huoneisto.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AsuntoController implements Initializable {
    @FXML
    private TextField asunnonTunnus;

    @FXML
    private TextField asukkaidenMaara;

    @FXML
    private Button vahvistaAsuntoPainike;

    @FXML
    private Button suljeAsuntoPainike;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        vahvistaAsuntoPainike.setOnAction(actionEvent -> vahvistaAsunto());
        suljeAsuntoPainike.setOnAction(actionEvent -> suljeAsunto());
    }

    private void vahvistaAsunto() {
    }

    private void suljeAsunto() {
    }
}

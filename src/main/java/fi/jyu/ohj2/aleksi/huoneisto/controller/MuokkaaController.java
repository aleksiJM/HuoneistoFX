package fi.jyu.ohj2.aleksi.huoneisto.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
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
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fi/jyu/ohj2/aleksi/huoneisto/lisaa-asukas.fxml")
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

    private void poistaAsukas() {
    }

    private void suljeMuokkaus() {
        Stage stage = (Stage) suljeMuokkausPainike.getScene().getWindow();
        stage.close();
    }
}

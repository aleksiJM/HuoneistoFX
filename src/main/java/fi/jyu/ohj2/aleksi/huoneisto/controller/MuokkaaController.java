package fi.jyu.ohj2.aleksi.huoneisto.controller;

import fi.jyu.ohj2.aleksi.huoneisto.App;
import fi.jyu.ohj2.aleksi.huoneisto.model.Asukas;
import fi.jyu.ohj2.aleksi.huoneisto.model.Asunto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    @FXML
    private TableView<Asukas> asukasTaulukko;

    private final ObservableList<Asukas> asukkaat = FXCollections.observableArrayList();

    private Asunto muokattavaAsunto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Asukas, String> nimiSarake = new TableColumn<>("Nimi");
        nimiSarake.setCellValueFactory(cd ->
                cd.getValue().nimiProperty()
        );
        asukasTaulukko.getColumns().add(nimiSarake);

        TableColumn<Asukas, Number> ikaSarake = new TableColumn<>("Ika");
        ikaSarake.setCellValueFactory(cd ->
                cd.getValue().ikaProperty()
        );
        asukasTaulukko.getColumns().add(ikaSarake);

        TableColumn<Asukas, String> yhteystiedotSarake = new TableColumn<>("Sahkoposti tai puhelinnumero");
        yhteystiedotSarake.setCellValueFactory(cd ->
                cd.getValue().yhteystiedotProperty()
        );
        asukasTaulukko.getColumns().add(yhteystiedotSarake);

        lisaaAsukasPainike.setOnAction(actionEvent -> lisaaAsukas());
        poistaAsukasPainike.setOnAction(actionEvent -> poistaAsukas());
        suljeMuokkausPainike.setOnAction(actionEvent -> suljeMuokkaus());
    }

    private void lisaaAsukas() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("lisaa-asukas.fxml"));
            Parent root = loader.load();

            AsukasController controller = loader.getController();

            Stage dialogi = new Stage();
            dialogi.setScene(new Scene(root));
            dialogi.setTitle("Lisaa asukas");

            dialogi.showAndWait();

            Asukas uusiAsukas = controller.getAsukas();

            if (uusiAsukas != null) {
                muokattavaAsunto.getAsukkaat().add(uusiAsukas);
            }

        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

    private void poistaAsukas() {
    }

    private void suljeMuokkaus() {
        Stage stage = (Stage) suljeMuokkausPainike.getScene().getWindow();
        stage.close();
    }

    public void setAsunto(Asunto asunto) {
        this.muokattavaAsunto = asunto;

        asukasTaulukko.setItems(muokattavaAsunto.getAsukkaat());
    }
}

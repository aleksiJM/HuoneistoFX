package fi.jyu.ohj2.aleksi.huoneisto.controller;

import fi.jyu.ohj2.aleksi.huoneisto.App;
import fi.jyu.ohj2.aleksi.huoneisto.model.Asukas;
import fi.jyu.ohj2.aleksi.huoneisto.model.Asunto;
import fi.jyu.ohj2.aleksi.huoneisto.model.Taloyhtio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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

    @FXML
    private Text otsikko;

    private Asunto muokattavaAsunto;

    private Asukas valittuAsukas;

    private Taloyhtio taloyhtio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Asukas, String> nimiSarake = new TableColumn<>("Nimi");
        nimiSarake.setCellValueFactory(cd ->
                cd.getValue().nimiProperty()
        );
        nimiSarake.setPrefWidth(100);
        asukasTaulukko.getColumns().add(nimiSarake);

        TableColumn<Asukas, Number> ikaSarake = new TableColumn<>("Ika");
        ikaSarake.setCellValueFactory(cd ->
                cd.getValue().ikaProperty()
        );
        ikaSarake.setPrefWidth(100);
        asukasTaulukko.getColumns().add(ikaSarake);

        TableColumn<Asukas, String> yhteystiedotSarake = new TableColumn<>("Sahkoposti tai puhelinnumero");
        yhteystiedotSarake.setCellValueFactory(cd ->
                cd.getValue().yhteystiedotProperty()
        );
        yhteystiedotSarake.setPrefWidth(300);
        asukasTaulukko.getColumns().add(yhteystiedotSarake);

        lisaaAsukasPainike.setOnAction(_ -> lisaaAsukas());
        poistaAsukasPainike.setOnAction(_ -> poistaAsukas());
        suljeMuokkausPainike.setOnAction(_ -> suljeMuokkaus());

        asukasTaulukko.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
                valittuAsukas = newVal
        );
    }

    private void lisaaAsukas() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("lisaa-asukas.fxml"));
            Parent root = loader.load();

            AsukasController controller = loader.getController();
            controller.setAsunto(muokattavaAsunto);

            Stage dialogi = new Stage();
            dialogi.setScene(new Scene(root));
            dialogi.setTitle("Lisaa asukas");

            dialogi.initModality(Modality.APPLICATION_MODAL);
            dialogi.showAndWait();

            taloyhtio.tallenna();
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

    private void poistaAsukas() {
        if (valittuAsukas == null) {
            return;
        }

        muokattavaAsunto.getAsukkaatObservable().remove(valittuAsukas);
        taloyhtio.tallenna();
    }

    private void suljeMuokkaus() {
        Stage stage = (Stage) suljeMuokkausPainike.getScene().getWindow();
        stage.close();
    }

    public void setAsunto(Asunto asunto) {
        this.muokattavaAsunto = asunto;

        asukasTaulukko.setItems(muokattavaAsunto.getAsukkaatObservable());
        otsikko.setText("Asunto: " + muokattavaAsunto.getTunnus());
    }

    public void setTaloyhtio(Taloyhtio taloyhtio) {
        this.taloyhtio = taloyhtio;
    }
}

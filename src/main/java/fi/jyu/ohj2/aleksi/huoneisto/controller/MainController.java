package fi.jyu.ohj2.aleksi.huoneisto.controller;

import fi.jyu.ohj2.aleksi.huoneisto.App;
import fi.jyu.ohj2.aleksi.huoneisto.model.Asunto;
import fi.jyu.ohj2.aleksi.huoneisto.model.Taloyhtio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button lisaaAsuntoPainike;

    @FXML
    private Button muokkaaAsuntoaPainike;

    @FXML
    private Button poistaAsuntoPainike;

    @FXML
    private TableView<Asunto> asuntoTaulukko;

    private final Taloyhtio taloyhtio = new Taloyhtio("taloyhtio.json");

    private Asunto valittuAsunto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn<Asunto, String> tunnusSarake = new TableColumn<>("Asunnon tunnus");
        tunnusSarake.setCellValueFactory(cd ->
                cd.getValue().tunnusProperty()
        );
        tunnusSarake.setPrefWidth(150);
        asuntoTaulukko.getColumns().add(tunnusSarake);

        TableColumn<Asunto, Number> maaraSarake = new TableColumn<>("Asukkaiden maara");
        maaraSarake.setCellValueFactory(cd ->
                cd.getValue().asukkaidenMaaraProperty()
        );
        maaraSarake.setPrefWidth(150);
        asuntoTaulukko.getColumns().add(maaraSarake);

        asuntoTaulukko.setRowFactory(_ -> {
            TableRow<Asunto> row = new TableRow<>();

            row.setOnMouseClicked(event -> {
                if (event.getButton().equals(MouseButton.PRIMARY) &&
                event.getClickCount() == 2 && !row.isEmpty()) {
                    muokkaaAsuntoa();
                }
            });

            return row;
        });

        lisaaAsuntoPainike.setOnAction(_ -> lisaaAsunto());
        muokkaaAsuntoaPainike.setOnAction(_ -> muokkaaAsuntoa());
        poistaAsuntoPainike.setOnAction(_ -> poistaAsunto());

        taloyhtio.lataa();
        asuntoTaulukko.setItems(taloyhtio.getAsunnot());

        asuntoTaulukko.getSelectionModel().selectedItemProperty().addListener((_, _, newVal) ->
                valittuAsunto = newVal
        );
    }

    private void lisaaAsunto() {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("lisaa-asunto.fxml"));
            Parent root = loader.load();

            AsuntoController controller = loader.getController();
            controller.setTaloyhtio(taloyhtio);

            Stage dialogi = new Stage();
            dialogi.setScene(new Scene(root));
            dialogi.setTitle("Lisaa asunto");

            dialogi.initModality(Modality.APPLICATION_MODAL);
            dialogi.showAndWait();

        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

    private void muokkaaAsuntoa() {
        try {
            if (valittuAsunto == null) {
                return;
            }

            FXMLLoader loader = new FXMLLoader(App.class.getResource("muokkaa.fxml"));
            Parent root = loader.load();

            MuokkaaController controller = loader.getController();
            controller.setAsunto(valittuAsunto);
            controller.setTaloyhtio(taloyhtio);

            Stage dialogi = new Stage();
            dialogi.setScene(new Scene(root));
            dialogi.setTitle("Muokkaa asuntoa: " + valittuAsunto.getTunnus());

            dialogi.initModality(Modality.APPLICATION_MODAL);
            dialogi.showAndWait();

        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

    private void poistaAsunto() {
        if (valittuAsunto == null) {
            return;
        }

        taloyhtio.poistaAsunto(valittuAsunto);
    }
}
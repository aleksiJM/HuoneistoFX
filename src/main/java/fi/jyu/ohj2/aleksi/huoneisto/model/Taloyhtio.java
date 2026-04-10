package fi.jyu.ohj2.aleksi.huoneisto.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Taloyhtio {

    private final ObservableList<Asunto> asunnot =
            FXCollections.observableArrayList();

    private final File tiedosto;
    private final ObjectMapper mapper = new ObjectMapper();

    public Taloyhtio(String tiedostoPolku) {
        this.tiedosto = new File(tiedostoPolku);

        asunnot.addListener((ListChangeListener<Asunto>) change -> {
            tallenna();
        });
    }

    public ObservableList<Asunto> getAsunnot() {
        return asunnot;
    }
    public void setAsunnot(List<Asunto> asunnotLista) {
        asunnot.setAll(asunnotLista);
    }
    public void lisaaAsunto(Asunto asunto) {
        asunnot.add(asunto);
    }
    public void poistaAsunto(Asunto asunto) {
        asunnot.remove(asunto);
    }

    public void tallenna() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(tiedosto, asunnot);
        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }

    public void lataa() {
        if (!tiedosto.exists()) return;

        try {
            List<Asunto> lista = mapper.readValue(
                    tiedosto,
                    new TypeReference<List<Asunto>>() {}
            );

            asunnot.setAll(lista);

        } catch (IOException error) {
            throw new RuntimeException(error);
        }
    }
}
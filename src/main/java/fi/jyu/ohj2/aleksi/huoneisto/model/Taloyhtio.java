package fi.jyu.ohj2.aleksi.huoneisto.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Taloyhtio {
    private final ObservableList<Asunto> asunnot =
            FXCollections.observableArrayList();

    public ObservableList<Asunto> getAsunnot() { return asunnot; }
    public void lisaaAsunto(Asunto asunto) { asunnot.add(asunto); }
    public void poistaAsunto(Asunto asunto) { asunnot.remove(asunto); }
}

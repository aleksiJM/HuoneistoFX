package fi.jyu.ohj2.aleksi.huoneisto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Asunto {
    private final StringProperty tunnus = new SimpleStringProperty("");
    private final ObservableList<Asukas> asukkaat = FXCollections.observableArrayList();
    private final ReadOnlyIntegerWrapper asukkaidenMaara = new ReadOnlyIntegerWrapper();

    public Asunto() {
        bindAsukkaidenMaara();
    }

    public Asunto(String tunnus) {
        setTunnus(tunnus);
        bindAsukkaidenMaara();
    }

    // aina kun lista muuttuu, määrä päivittyy
    private void bindAsukkaidenMaara() {
        asukkaidenMaara.bind(
                javafx.beans.binding.Bindings.size(asukkaat)
        );
    }

    // tunnus
    public String getTunnus() { return tunnus.get(); }
    public void setTunnus(String tunnus) { this.tunnus.set(tunnus); }
    public StringProperty tunnusProperty() { return tunnus; }

    // asukkaat OBSERVABLE
    @JsonIgnore
    public ObservableList<Asukas> getAsukkaatObservable() { return asukkaat; }
    public void lisaaAsukas(Asukas asukas) { asukkaat.add(asukas); }
    public void poistaAsukas(Asukas asukas) { asukkaat.remove(asukas); }

    // automaattisesti päivittyvä määrä
    @JsonIgnore
    public int getAsukkaidenMaara() { return asukkaidenMaara.get(); }
    public ReadOnlyIntegerWrapper asukkaidenMaaraProperty() { return asukkaidenMaara; }

    // JSON varten
    @JsonProperty("asukkaat")
    public List<Asukas> getAsukkaat() { return new ArrayList<>(asukkaat); }
    public void setAsukkaat(List<Asukas> asukkaatLista) { asukkaat.setAll(asukkaatLista); }
}
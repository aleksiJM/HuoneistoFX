package fi.jyu.ohj2.aleksi.huoneisto.model;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    public ObservableList<Asukas> getAsukkaat() { return asukkaat; }
    public void lisaaAsukas(Asukas asukas) { asukkaat.add(asukas); }
    public void poistaAsukas(Asukas asukas) { asukkaat.remove(asukas); }

    // automaattisesti päivittyvä määrä
    public int getAsukkaidenMaara() { return asukkaidenMaara.get(); }
    public ReadOnlyIntegerWrapper asukkaidenMaaraProperty() { return asukkaidenMaara; }
}
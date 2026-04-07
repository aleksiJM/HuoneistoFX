package fi.jyu.ohj2.aleksi.huoneisto.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Asukas {
    private final StringProperty nimi = new SimpleStringProperty("");
    private final IntegerProperty ika = new SimpleIntegerProperty(0);
    private final StringProperty yhteystiedot = new SimpleStringProperty("");

    public Asukas() {}

    public Asukas(String nimi, int ika, String yhteystiedot) {
        setNimi(nimi);
        setIka(ika);
        setYhteystiedot(yhteystiedot);
    }

    public String getNimi() { return this.nimi.get(); }
    public void setNimi(String nimi) { this.nimi.set(nimi); }
    public StringProperty nimiProperty() { return this.nimi; }

    public Integer getIka() { return this.ika.get(); }
    public void setIka(int ika) { this.ika.set(ika); }
    public IntegerProperty ikaProperty() { return this.ika; }

    public String getYhteystiedot() { return this.yhteystiedot.get(); }
    public void setYhteystiedot(String yhteystiedot) { this.yhteystiedot.set(yhteystiedot); }
    public StringProperty yhteystiedotProperty() { return this.yhteystiedot; }
}
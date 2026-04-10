import fi.jyu.ohj2.aleksi.huoneisto.model.Asukas;
import fi.jyu.ohj2.aleksi.huoneisto.model.Asunto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AsuntoTest {

    @Test
    void asukasListaTesti() {
        Asunto asunto = new Asunto("A1");
        Asukas asukas = new Asukas("Jari", 30, "jari@test.com");

        asunto.lisaaAsukas(asukas);

        assertEquals(1, asunto.getAsukkaat().size());
        assertEquals(asukas, asunto.getAsukkaat().getFirst());
    }

    @Test
    void asukkaidenMaaraTesti() {
        Asunto asunto = new Asunto("A1");

        asunto.lisaaAsukas(new Asukas("Jari", 30, "+3581234567"));
        asunto.lisaaAsukas(new Asukas("Jori", 50, "+3587654321"));

        assertEquals(2, asunto.getAsukkaidenMaara());
    }
}
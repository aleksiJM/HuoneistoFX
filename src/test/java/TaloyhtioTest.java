import fi.jyu.ohj2.aleksi.huoneisto.model.Asunto;
import fi.jyu.ohj2.aleksi.huoneisto.model.Taloyhtio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaloyhtioTest {

    @Test
    void asuntoListaTesti() {
        Taloyhtio taloyhtio = new Taloyhtio("taloyhtio-test.json");
        Asunto asunto = new Asunto("A1");

        taloyhtio.lisaaAsunto(asunto);

        assertTrue(taloyhtio.getAsunnot().contains(asunto));
    }

    @Test
    void asuntoPoistoTesti() {
        Taloyhtio taloyhtio = new Taloyhtio("taloyhtio-test.json");
        Asunto asunto = new Asunto("A1");

        taloyhtio.lisaaAsunto(asunto);
        taloyhtio.poistaAsunto(asunto);

        assertFalse(taloyhtio.getAsunnot().contains(asunto));
    }
}

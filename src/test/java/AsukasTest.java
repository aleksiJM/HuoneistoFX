import fi.jyu.ohj2.aleksi.huoneisto.model.Asukas;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AsukasTest {

    @Test
    void konstruktoriTesti() {
        Asukas asukas = new Asukas("Jari", 30, "jari@test.com");

        assertEquals("Jari", asukas.getNimi());
        assertEquals(30, asukas.getIka());
        assertEquals("jari@test.com", asukas.getYhteystiedot());
    }
}
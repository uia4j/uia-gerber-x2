package uia.gerber.x2;

import java.io.IOException;

import org.junit.Test;

public class GerberX2RescaleTest {

    @Test
    public void test() throws IOException {
        GerberX2Rescale rescale = new GerberX2Rescale("samples/plp/ML1_scaled.gbr", 3, 6);
        GerberX2FileReader r = new GerberX2FileReader(rescale);
        r.run("samples/plp/ML1.gbr");
    }
}

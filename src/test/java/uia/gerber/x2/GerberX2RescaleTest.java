package uia.gerber.x2;

import java.io.IOException;

import org.junit.Test;

import uia.gerber.x2.GerberX2FileReader.FormatMode;

public class GerberX2RescaleTest {

    @Test
    public void test() throws IOException {
        FormatMode fm1 = new GerberX2FileReader().findFormatMode("samples/plp/BIG.gbr");
        System.out.println(fm1.format);

        GerberX2Rescale rescale = new GerberX2Rescale("samples/plp/BIG_scaled.gbr", fm1.format.intDigi(), 3);
        GerberX2FileReader r = new GerberX2FileReader(rescale);
        r.run("samples/plp/BIG.gbr");

        FormatMode fm2 = new GerberX2FileReader().findFormatMode("samples/plp/BIG_scaled");
        System.out.println(fm2.format);
    }

    @Test
    public void test2() throws IOException {
        FormatMode fm2 = new GerberX2FileReader().findFormatMode("samples/plp/BIG_scaled.gbr");
        System.out.println(fm2.format);
    }
}

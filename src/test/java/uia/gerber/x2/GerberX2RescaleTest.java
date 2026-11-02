package uia.gerber.x2;

import java.io.IOException;

import org.junit.Test;

import uia.gerber.x2.GerberX2FileReader.FormatMode;

public class GerberX2RescaleTest {

    @Test
    public void test() throws IOException {
        FormatMode fm1 = new GerberX2FileReader().findFormatMode("samples/gerber1.gbr");
        System.out.println(fm1.format);

        new GerberX2Rescale("samples/gerber1_scale.gbr", fm1.format.intDigi(), 3).build("samples/gerber1.gbr");

        FormatMode fm2 = new GerberX2FileReader().findFormatMode("samples/gerber1_scale.gbr");
        System.out.println(fm2.format);
    }
}

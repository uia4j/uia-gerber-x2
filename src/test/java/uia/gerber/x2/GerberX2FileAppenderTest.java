package uia.gerber.x2;

import java.io.IOException;

import org.junit.Test;

public class GerberX2FileAppenderTest {

    @Test
    public void test() throws IOException {
        GerberX2FileAppender appender = new GerberX2FileAppender("samples/plp/ML1_new.gbr", 3, 6);
        GerberX2FileWriter writer = appender.read("samples/plp/ML1.gbr");
        writer.getGraphics().loadPolarity(false);
        writer.close();
    }
}

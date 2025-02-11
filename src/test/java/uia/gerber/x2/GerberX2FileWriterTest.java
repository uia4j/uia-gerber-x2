package uia.gerber.x2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

import uia.gerber.x2.GerberX2FileWriter;
import uia.gerber.x2.builder.CommonGraphics;

public class GerberX2FileWriterTest {

    @Test
    public void test1() throws IOException {
        GerberX2FileWriter writer = new GerberX2FileWriter(new FileOutputStream(new File("samples/gerber1.gbr"), false))
                .fs(4, 6)
                .description("TEST1 - Region");

        writer.start();

        CommonGraphics cg = writer.getGraphics();
        // region 1
        cg.loadPolarity(true);
        cg.createRegion(writer.xy(0.1), writer.xy(0.1))
                .lineTo(writer.xy(0.5), writer.xy(0.1))
                .lineTo(writer.xy(0.5), writer.xy(0.4))
                .ccwTo(writer.xy(0.4), writer.xy(0.5), writer.xy(0.4), writer.xy(0.4))
                .lineTo(writer.xy(0.1), writer.xy(0.5))
                .lineTo(writer.xy(0.1), writer.xy(0.1))
                .close();

        // region 2
        cg.loadPolarity(false);
        cg.createRegion(writer.xy(0.4), writer.xy(0.4))
                .lineTo(writer.xy(0.3), writer.xy(0.4))
                .lineTo(writer.xy(0.3), writer.xy(0.3))
                .lineTo(writer.xy(0.4), writer.xy(0.3))
                .lineTo(writer.xy(0.4), writer.xy(0.4))
                .close();

        writer.close();
    }

    @Test
    public void test2() throws IOException {
        GerberX2FileWriter writer = new GerberX2FileWriter(new FileOutputStream(new File("samples/gerber2.gbr"), false))
                .fs(2, 6)
                .description("TEST2 - AB");

        writer.start();

        CommonGraphics cg = writer.getGraphics();

        // define AB
        CommonGraphics bg = cg.defineBlock(12);
        // region
        bg.loadPolarity(true);
        bg.createRegion(writer.xy(0.1), writer.xy(0.1))
                .lineTo(writer.xy(0.5), writer.xy(0.1))
                .lineTo(writer.xy(0.5), writer.xy(0.4))
                .ccwTo(writer.xy(0.4), writer.xy(0.5), writer.xy(0.4), writer.xy(0.4))
                .lineTo(writer.xy(0.1), writer.xy(0.5))
                .lineTo(writer.xy(0.1), writer.xy(0.1))
                .close();
        // region
        bg.createRegion(writer.xy(0.5), writer.xy(0.5))
                .lineTo(writer.xy(0.5), writer.xy(0.6))
                .lineTo(writer.xy(0.6), writer.xy(0.6))
                .lineTo(writer.xy(0.6), writer.xy(0.5))
                .lineTo(writer.xy(0.5), writer.xy(0.5))
                .close();
        // region
        bg.loadPolarity(false);
        bg.createRegion(writer.xy(0.4), writer.xy(0.4))
                .lineTo(writer.xy(0.3), writer.xy(0.4))
                .lineTo(writer.xy(0.3), writer.xy(0.3))
                .lineTo(writer.xy(0.4), writer.xy(0.3))
                .lineTo(writer.xy(0.4), writer.xy(0.4))
                .close();
        // close AB
        bg.close();

        cg.loadPolarity(true); // important
        cg.dnn(12)
                .flash(writer.xy(0), writer.xy(0))
                .flash(writer.xy(1), writer.xy(0))
                .flash(writer.xy(0), writer.xy(1))
                .flash(writer.xy(1), writer.xy(1));

        writer.close();
    }

    @Test
    public void test3() throws IOException {
        GerberX2FileWriter writer = new GerberX2FileWriter(new FileOutputStream(new File("samples/gerber3.gbr"), false))
                .fs(2, 6)
                .description("TEST3 - AD");

        writer.start();

        CommonGraphics cg = writer.getGraphics()
                .defineCircle(10, new BigDecimal("10"))
                .defineCircle(11, new BigDecimal("10"), new BigDecimal("6"))
                .defineRectangle(12, new BigDecimal("10"), new BigDecimal("10"))
                .defineRectangle(13, new BigDecimal("10"), new BigDecimal("10"), new BigDecimal("6"))
                .defineRectangle(14, new BigDecimal("8"), new BigDecimal("8"));

        cg.loadPolarity(true);
        cg.dnn(10).flash(writer.xy(0), writer.xy(0));
        cg.dnn(11).flash(writer.xy(20), writer.xy(20));
        cg.dnn(12)
                .flash(writer.xy(0), writer.xy(20))
                .flash(writer.xy(10), writer.xy(10));
        cg.dnn(13).flash(writer.xy(20), writer.xy(0));

        cg.loadPolarity(false);
        cg.dnn(14).flash(writer.xy(10), writer.xy(10));

        writer.close();

    }

    @Test
    public void test4() throws IOException {
        GerberX2FileWriter writer = new GerberX2FileWriter(new FileOutputStream(new File("samples/gerber4.gbr"), false))
                .fs(2, 6)
                .description("TEST4 - AB without Region");

        writer.start();

        // define ADs in common graphics
        CommonGraphics cg = writer.getGraphics()
                .defineCircle(10, new BigDecimal("6"))
                .defineRectangle(12, new BigDecimal("10"), new BigDecimal("10"))
                .defineRectangle(14, new BigDecimal("8"), new BigDecimal("8"));

        // define AB
        CommonGraphics bg = cg.defineBlock(101);
        // AD012
        bg.loadPolarity(true);
        bg.dnn(12).flash(writer.xy(0), writer.xy(0));
        // AD014
        bg.loadPolarity(false);
        bg.dnn(14).flash(writer.xy(0), writer.xy(0));
        // AD010
        bg.loadPolarity(true);
        bg.dnn(10).flash(writer.xy(0), writer.xy(0));
        // close AB
        bg.close();

        cg.loadPolarity(true);
        cg.dnn(101)
                .flash(writer.xy(0), writer.xy(0))
                .flash(writer.xy(15), writer.xy(15));

        writer.close();
    }

    @Test
    public void test5() throws IOException {
        // rectangle #1, using aperture
        GerberX2FileWriter writer = new GerberX2FileWriter(new FileOutputStream(new File("samples/gerber5.gbr"), false))
                .fs(2, 6)
                .description("TEST5 - Rectangle using AD");

        writer.start();

        CommonGraphics cg = writer.getGraphics();

        // define AB
        // define ADs in AB graphics
        cg.defineBlock(170)
                .defineRectangle(10, BigDecimal.valueOf(0.01), BigDecimal.valueOf(0.01))    // AD010, important, line width
                .loadPolarity(true)
                .dnn(10)
                .move(writer.xy(0), writer.xy(0))
                .lineTo(writer.xy(0.176), null)
                .lineTo(null, writer.xy(0.276))
                .lineTo(writer.xy(0), null)
                .lineTo(null, writer.xy(0))
                .close();

        cg.dnn(170).flash(writer.xy(0), writer.xy(0));

        writer.close();
    }

    @Test
    public void test6() throws IOException {
        // rectangle #2, using region
        int scale = 1000;
        GerberX2FileWriter writer = new GerberX2FileWriter(new FileOutputStream(new File("samples/gerber6.gbr"), false))
                .fs(2, 6)
                .description("TEST6 - Rectangle using Region");

        writer.start();

        CommonGraphics cg = writer.getGraphics();

        // define AB
        CommonGraphics bg = cg.defineBlock(170);
        // rectangle 1
        bg.loadPolarity(true);
        bg.createRegion(writer.xy(-0.00065 * scale), writer.xy(-0.00065 * scale))
                .lineTo(writer.xy(0.00065 * scale), writer.xy(-0.00065 * scale))
                .lineTo(writer.xy(0.00065 * scale), writer.xy(0.00065 * scale))
                .lineTo(writer.xy(-0.00065 * scale), writer.xy(0.00065 * scale))
                .lineTo(writer.xy(-0.00065 * scale), writer.xy(-0.00065 * scale))
                .close();
        // rectangle 2
        bg.loadPolarity(false);
        bg.createRegion(writer.xy(-0.0006 * scale), writer.xy(-0.0006 * scale))
                .lineTo(writer.xy(0.0006 * scale), writer.xy(-0.0006 * scale))
                .lineTo(writer.xy(0.0006 * scale), writer.xy(0.0006 * scale))
                .lineTo(writer.xy(-0.0006 * scale), writer.xy(0.0006 * scale))
                .lineTo(writer.xy(-0.0006 * scale), writer.xy(-0.0006 * scale))
                .close();
        // close AB
        bg.close();

        cg.loadPolarity(true)
                .dnn(170)
                .flash(writer.xy(0), writer.xy(0))
                .flash(writer.xy(2), writer.xy(2))
                .flash(writer.xy(2), writer.xy(0))
                .flash(writer.xy(0), writer.xy(2));

        writer.close();
    }

    @Test
    public void test7() throws IOException {
        int scale = 1000;
        GerberX2FileWriter writer = new GerberX2FileWriter(new FileOutputStream(new File("samples/gerber7.gbr"), false))
                .fs(2, 6)
                .description("TEST7 - Special");

        writer.start();

        CommonGraphics cg = writer.getGraphics();

        // define AB
        CommonGraphics bg = cg.defineBlock(130);
        // rectangle 1
        bg.loadPolarity(true);
        bg.createRegion(writer.xy(-0.00065 * scale), writer.xy(-0.00065 * scale))
                .lineTo(writer.xy(0.00065 * scale), writer.xy(-0.00065 * scale))
                .lineTo(writer.xy(0.00065 * scale), writer.xy(0.00065 * scale))
                .lineTo(writer.xy(-0.00065 * scale), writer.xy(0.00065 * scale))
                .lineTo(writer.xy(-0.00065 * scale), writer.xy(-0.00065 * scale))
                .close();
        // rectangle 2
        bg.loadPolarity(false);
        bg.createRegion(writer.xy(-0.0006 * scale), writer.xy(-0.0006 * scale))
                .lineTo(writer.xy(0.0006 * scale), writer.xy(-0.0006 * scale))
                .lineTo(writer.xy(0.0006 * scale), writer.xy(0.0006 * scale))
                .lineTo(writer.xy(-0.0006 * scale), writer.xy(0.0006 * scale))
                .lineTo(writer.xy(-0.0006 * scale), writer.xy(-0.0006 * scale))
                .close();
        // circle
        bg.defineCircle(131, BigDecimal.valueOf(0.001 * scale));
        bg.loadPolarity(true);
        bg.dnn(131);
        bg.flashMO(0.0, 0.0);
        // special
        bg.loadPolarity(false);
        bg.createRegion(writer.xy(0.0), writer.xy(0.0))
                .lineTo(writer.xy(0.00025 * scale), writer.xy(0))
                .ccwTo(writer.xy(0), writer.xy(0.00025 * scale), writer.xy(0), writer.xy(0))
                .lineTo(writer.xy(0), writer.xy(0))
                .lineTo(writer.xy(-0.00025 * scale), writer.xy(0))
                .ccwTo(writer.xy(0), writer.xy(-0.00025 * scale), writer.xy(0), writer.xy(0))
                .lineTo(writer.xy(0), writer.xy(0))
                .close();
        // close AB
        bg.close();

        cg.loadPolarity(true);
        cg.dnn(130)
                .flash(writer.xy(0), writer.xy(0))
                .flash(writer.xy(2), writer.xy(0))
                .flash(writer.xy(2), writer.xy(2))
                .flash(writer.xy(0), writer.xy(2));

        writer.close();
    }

    @Test
    public void test8() throws IOException {
        GerberX2FileWriter writer = new GerberX2FileWriter(new FileOutputStream(new File("samples/gerber8.gbr"), false))
                .fs(2, 6)
                .description("TEST8");

        writer.start();

        writer.getGraphics()
                .defineSquare(11, BigDecimal.valueOf(1.0))
                .dnn(11)
                .rect(writer.xy(-10), writer.xy(-30), writer.xy(20), writer.xy(50))
                .close();

        writer.close();
    }

    @Test
    public void test9() throws IOException {
        GerberX2FileWriter writer = new GerberX2FileWriter(System.out)
                .fs(2, 6)
                .description("TEST9 - Text");

        writer.start();

        writer.getGraphics()
                .createText("Arial")
                .text("ABCDEFG", writer.xy(0), writer.xy(10), writer.xy(100), writer.xy(10))
                .close();

        writer.close();
    }
}

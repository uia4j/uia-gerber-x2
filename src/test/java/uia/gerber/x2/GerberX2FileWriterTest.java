package uia.gerber.x2;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

import uia.gerber.x2.builder.CommonGraphics;
import uia.gerber.x2.builder.StepRepeatGraphics;

public class GerberX2FileWriterTest {

    @Test
    public void test1() throws IOException {
        GerberX2FileWriter writer = new GerberX2FileWriter(System.out);
        writer.setDescription("TEST1 - Region");

        writer.start();

        CommonGraphics x2g = writer.getGraphics();

        // region #1
        x2g.createRegion(writer.x(0.1), writer.y(0.1))
                .lineTo(writer.x(0.1), writer.y(0.5))
                .lineTo(writer.x(0.4), null)
                .cwTo(writer.x(0.5), writer.y(0.4), null, writer.y(-0.1))
                .lineTo(writer.x(0.5), writer.y(0.1))
                .lineTo(writer.x(0.1), null);

        x2g.loadPolarity(false);

        // region #2
        x2g.createRegion(writer.x(0.3), writer.y(0.3))
                .lineTo(null, writer.y(0.4))
                .lineTo(writer.x(0.4), null)
                .lineTo(writer.x(0.4), writer.y(0.3))
                .lineTo(writer.x(0.3), null);

        writer.stop();
    }

    @Test
    public void test2() throws IOException {
        GerberX2FileWriter writer = new GerberX2FileWriter(System.out);
        writer.setXValuer(2, 6);
        writer.setYValuer(2, 6);
        writer.setDescription("TEST2 - Common");

        writer.start();

        CommonGraphics x2g = writer.getGraphics();
        x2g.defineCircle(10, new BigDecimal("10"), new BigDecimal("5"));
        x2g.defineCircle(11, new BigDecimal("1"));
        x2g.dnn(11);
        x2g.move(
                writer.x(BigDecimal.valueOf(-25)),
                writer.y(BigDecimal.valueOf(-1)));
        x2g.lineTo(
                writer.x(BigDecimal.valueOf(25)),
                writer.y(BigDecimal.valueOf(1)));
        x2g.dnn(10);
        x2g.flash(
                writer.y(BigDecimal.ZERO),
                writer.y(BigDecimal.ZERO));

        writer.stop();
    }

    @Test
    public void test3() throws IOException {
        GerberX2FileWriter writer = new GerberX2FileWriter(System.out);
        writer.setXValuer(2, 6);
        writer.setYValuer(2, 6);
        writer.setDescription("TEST3#1 TA");
        writer.addAttribute(".Part", "PCB");
        writer.addAttribute(".AperFunction", "SMD");

        writer.start();
        writer.stop();
    }

    @Test
    public void test4_1() throws IOException {
        GerberX2FileWriter writer = new GerberX2FileWriter(System.out);
        writer.setXValuer(2, 6);
        writer.setYValuer(2, 6);
        writer.setDescription("TEST4#1");

        writer.start();

        CommonGraphics x2g = writer.getGraphics();
        x2g.defineCircle(10, new BigDecimal("10"), new BigDecimal("5"));
        x2g.defineCircle(11, new BigDecimal("1"));

        // SR - START
        StepRepeatGraphics graphics = x2g.createStepRepeat(3, 2, 1, 2);
        // Common
        graphics.dnn(11);
        graphics.move(
                writer.x(BigDecimal.valueOf(-25)),
                writer.y(BigDecimal.valueOf(-1)));
        graphics.lineTo(
                writer.x(BigDecimal.valueOf(25)),
                writer.y(BigDecimal.valueOf(1)));
        graphics.dnn(10);
        graphics.flash(
                writer.y(BigDecimal.ZERO),
                writer.y(BigDecimal.ZERO));
        // SR - END

        x2g.move((long) 0, (long) 0);

        writer.stop();
    }

    @Test
    public void test4_2() throws IOException {
        GerberX2FileWriter writer = new GerberX2FileWriter(System.out);
        writer.setXValuer(2, 6);
        writer.setYValuer(2, 6);
        writer.setDescription("TEST4#2");

        writer.start();
        CommonGraphics x2g = writer.getGraphics();

        // SR - START
        StepRepeatGraphics srg = x2g.createStepRepeat(3, 2, 1, 2);
        // region #1
        srg.createRegion(writer.x(0.1), writer.y(0.1))
                .lineTo(writer.x(0.1), writer.y(0.5))
                .lineTo(writer.x(0.4), null)
                .cwTo(writer.x(0.5), writer.y(0.4), null, writer.y(-0.1))
                .lineTo(writer.x(0.5), writer.y(0.1))
                .lineTo(writer.x(0.1), null);

        srg.loadPolarity(false);

        // region #2
        srg.createRegion(writer.x(0.3), writer.y(0.3))
                .lineTo(null, writer.y(0.4))
                .lineTo(writer.x(0.4), null)
                .lineTo(writer.x(0.4), writer.y(0.3))
                .lineTo(writer.x(0.3), null);
        // SR - END

        writer.stop();
    }
}

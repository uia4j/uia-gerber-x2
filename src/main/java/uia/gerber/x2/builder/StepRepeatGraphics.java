package uia.gerber.x2.builder;

import java.io.IOException;
import java.math.BigDecimal;

import uia.gerber.x2.GerberX2FileWriter;

/**
 * Step Repeat(SR) graphics.
 *
 * @author Kyle K. Lin
 *
 */
public class StepRepeatGraphics implements GerberX2Graphics {

    GerberX2FileWriter writer;

    CommonGraphics x2g;

    public StepRepeatGraphics(int x, int y, long i, long j, GerberX2FileWriter writer, int initState) throws IOException {
        this.writer = writer;
        this.x2g = new CommonGraphics(writer, initState);
        writer.getOutputStream().write(String.format("%%SRX%sY%sI%sJ%s*%%\n", x, y, i, j).getBytes());
    }

    public void defineCircle(int no, BigDecimal diameter) throws IOException {
        this.x2g.defineCircle(no, diameter);
    }

    public void defineCircle(int no, BigDecimal diameter, BigDecimal holeDiameter) throws IOException {
        this.x2g.defineCircle(no, diameter, holeDiameter);
    }

    public void defineRectangle(int no, BigDecimal width, BigDecimal height) throws IOException {
        this.x2g.defineRectangle(no, width, height);
    }

    public void defineRectangle(int no, BigDecimal width, BigDecimal height, BigDecimal holeDiameter) throws IOException {
        this.x2g.defineRectangle(no, width, height, holeDiameter);
    }

    public void defineObound(int no, BigDecimal width, BigDecimal height) throws IOException {
        this.x2g.defineObound(no, width, height);
    }

    public void defineObound(int no, BigDecimal width, BigDecimal height, BigDecimal holeDiameter) throws IOException {
        this.x2g.defineObound(no, width, height, holeDiameter);
    }

    public void definePolygon(int no, long diameter, Long holeDiameter) throws IOException {
        // TODO:
    }

    public void loadPolarity(boolean dark) throws IOException {
        this.x2g.loadPolarity(dark);
    }

    public void dnn(int no) throws IOException {
        this.x2g.dnn(no);
    }

    public void lineTo(Long x, Long y) throws IOException {
        this.x2g.lineTo(x, y);
    }

    public void cwArc(Long x, Long y, Long i, Long j) throws IOException {
        this.cwArc(x, y, i, j);
    }

    public void ccwArc(Long x, Long y, Long i, Long j) throws IOException {
        this.ccwArc(x, y, i, j);
    }

    public void move(Long x, Long y) throws IOException {
        this.x2g.move(x, y);
    }

    public void flash(Long x, Long y) throws IOException {
        this.x2g.flash(x, y);
    }

    public RegionGraphics createRegion(long x, long y) throws IOException {
        return this.x2g.createRegion(x, y);
    }

    @Override
    public int lastState() {
        return this.x2g.lastState();
    }

    @Override
    public void close() throws IOException {
        this.x2g.close();
        this.x2g = null;

        this.writer.getOutputStream().write("%SR*%\n".getBytes());
        this.writer = null;

    }

}

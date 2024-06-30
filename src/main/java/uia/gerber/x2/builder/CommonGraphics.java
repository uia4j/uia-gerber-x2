package uia.gerber.x2.builder;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

import uia.gerber.x2.GerberX2FileWriter;
import uia.gerber.x2.model.ADCircle;
import uia.gerber.x2.model.ADObound;
import uia.gerber.x2.model.ADRectangle;
import uia.gerber.x2.model.D01Plot;
import uia.gerber.x2.model.D02Move;
import uia.gerber.x2.model.D03Flash;
import uia.gerber.x2.model.Dnn;
import uia.gerber.x2.model.G01;
import uia.gerber.x2.model.G02;
import uia.gerber.x2.model.G03;
import uia.gerber.x2.model.LP;

/**
 * Common graphics.
 *
 * @author Kyle K. Lin
 *
 */
public class CommonGraphics implements GerberX2Graphics {

    GerberX2FileWriter writer;

    OutputStream out;

    private int lastState;

    private GerberX2Graphics lastGS;

    public CommonGraphics(GerberX2FileWriter writer) throws IOException {
        this.writer = writer;
        this.out = writer.getOutputStream();
    }

    public CommonGraphics(GerberX2FileWriter writer, int initState) throws IOException {
        this.writer = writer;
        this.out = writer.getOutputStream();
        this.lastState = initState;
    }

    public void defineCircle(int no, BigDecimal diameter) throws IOException {
        defineCircle(no, diameter, null);
    }

    public void defineCircle(int no, BigDecimal diameter, BigDecimal holeDiameter) throws IOException {
        if (!this.writer.addDnn(no)) {
            throw new IOException(String.format("ADD%03d already exists", no));
        }
        endLast();
        new ADCircle(no, diameter, holeDiameter).write(this.out);
    }

    public void defineRectangle(int no, BigDecimal width, BigDecimal height) throws IOException {
        defineRectangle(no, width, height, null);
    }

    public void defineRectangle(int no, BigDecimal width, BigDecimal height, BigDecimal holeDiameter) throws IOException {
        if (!this.writer.addDnn(no)) {
            throw new IOException(String.format("ADD%03d already exists", no));
        }
        endLast();
        new ADRectangle(no, width, height, holeDiameter).write(this.out);
    }

    public void defineObound(int no, BigDecimal width, BigDecimal height) throws IOException {
        defineObound(no, width, height, null);
    }

    public void defineObound(int no, BigDecimal width, BigDecimal height, BigDecimal holeDiameter) throws IOException {
        if (!this.writer.addDnn(no)) {
            throw new IOException(String.format("ADD%03d already exists", no));
        }
        endLast();
        new ADObound(no, width, height, holeDiameter).write(this.out);
    }

    public void definePolygon(int no, long diameter, Long holeDiameter) throws IOException {
        // TODO:
    }

    public void loadPolarity(boolean dark) throws IOException {
        endLast();
        if (dark) {
            // dark
            new LP("D").write(this.out);
        }
        else {
            // clear
            new LP("C").write(this.out);
        }
    }

    public void dnn(int no) throws IOException {
        endLast();
        if (!this.writer.contains(no)) {
            throw new IOException(String.format("D%03d not found", no));
        }
        new Dnn(no).write(this.out);
    }

    public void lineTo(Long x, Long y) throws IOException {
        endLast();
        if (this.lastState != 1) {
            new G01().write(this.out);
        }
        new D01Plot(x, y).write(this.out);
        this.lastState = 1;
    }

    public void cwArc(Long x, Long y, Long i, Long j) throws IOException {
        endLast();
        if (this.lastState == 1) {
            new G02().write(this.out);
        }
        else if (this.lastState == 3) {
            new G02().write(this.out);
        }
        new D01Plot(x, y, i, j).write(this.out);
        this.lastState = 2;
    }

    public void ccwArc(Long x, Long y, Long i, Long j) throws IOException {
        endLast();
        if (this.lastState == 1) {
            new G03().write(this.out);
        }
        else if (this.lastState == 2) {
            new G03().write(this.out);
        }
        new D01Plot(x, y, i, j).write(this.out);
        this.lastState = 3;
    }

    public void move(Long x, Long y) throws IOException {
        endLast();
        new D02Move(x, y).write(this.out);
    }

    public void flash(Long x, Long y) throws IOException {
        endLast();
        new D03Flash(x, y).write(this.out);
    }

    public RegionGraphics createRegion(long x, long y) throws IOException {
        endLast();
        RegionGraphics gs = new RegionGraphics(x, y, this.lastState, this.out);
        this.lastGS = gs;
        return gs;
    }

    public StepRepeatGraphics createStepRepeat(int x, int y, long i, long j) throws IOException {
        endLast();
        StepRepeatGraphics gs = new StepRepeatGraphics(x, y, i, j, this.writer, this.lastState);
        this.lastGS = gs;
        return gs;
    }

    private void endLast() throws IOException {
        if (this.lastGS != null) {
            this.lastState = this.lastGS.lastState();
            this.lastGS.close();
            this.lastGS = null;
        }
    }

    @Override
    public int lastState() {
        return this.lastState;
    }

    @Override
    public void close() throws IOException {
        if (this.lastGS != null) {
            this.lastState = this.lastGS.lastState();
            this.lastGS.close();
            this.lastGS = null;
        }
        this.writer = null;
    }

}

package uia.gerber.x2.builder;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

import uia.gerber.x2.GerberX2FileWriter;
import uia.gerber.x2.GerberX2Statement;
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
import uia.gerber.x2.model.LM;
import uia.gerber.x2.model.LM.MirrorType;
import uia.gerber.x2.model.LNLayer;
import uia.gerber.x2.model.LP;
import uia.gerber.x2.model.LR;

/**
 * Common graphics.
 *
 * @author Kyle K. Lin
 *
 */
public class CommonGraphics implements GerberX2Graphics {

    GerberX2FileWriter writer;

    OutputStream out;

    int lastState;

    GerberX2Graphics lastGS;

    private Long lastX;

    private Long lastY;

    /**
     * The constructor.
     *
     * @param writer
     */
    public CommonGraphics(GerberX2FileWriter writer) {
        this.writer = writer;
        this.out = writer.getOutputStream();
    }

    /**
     * The constructor.
     *
     * @param writer
     * @param initState
     */
    public CommonGraphics(GerberX2FileWriter writer, int initState) {
        this.writer = writer;
        this.out = writer.getOutputStream();
        this.lastState = initState;
    }

    /**
     * Defines a circular aperture.
     *
     * @param dCode The d code.
     * @param moDiameter
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics defineCircle(int dCode, BigDecimal moDiameter) throws IOException {
        if (!this.writer.addDnn(dCode)) {
            throw new IOException(String.format("ADD%03d already exists", dCode));
        }
        endLast();
        new ADCircle(
                dCode,
                moDiameter.setScale(this.writer.fs().decDigi(), BigDecimal.ROUND_HALF_UP),
                null).write(this.out);
        return this;
    }

    /**
     * Defines a circular aperture.
     *
     * @param dCode The d code.
     * @param moDiameter
     * @param moHoleDiameter
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics defineCircle(int dCode, BigDecimal moDiameter, BigDecimal moHoleDiameter) throws IOException {
        if (!this.writer.addDnn(dCode)) {
            throw new IOException(String.format("ADD%03d already exists", dCode));
        }
        endLast();
        new ADCircle(
                dCode,
                moDiameter.setScale(this.writer.fs().decDigi(), BigDecimal.ROUND_HALF_UP),
                moHoleDiameter.setScale(this.writer.fs().decDigi(), BigDecimal.ROUND_HALF_UP)).write(this.out);
        return this;
    }

    public boolean containsDnn(int dCode) {
        return this.writer.contains(dCode);
    }

    public CommonGraphics defineSquare(int dCode, BigDecimal moWidth) throws IOException {
        if (!this.writer.addDnn(dCode)) {
            throw new IOException(String.format("ADD%03d already exists", dCode));
        }
        endLast();
        BigDecimal min = BigDecimal.valueOf(Math.pow(10, -this.writer.fs().decDigi()))
                .setScale(this.writer.fs().decDigi(), BigDecimal.ROUND_HALF_UP);
        BigDecimal _w = moWidth.compareTo(min) < 0
                ? min
                : moWidth.setScale(this.writer.fs().decDigi(), BigDecimal.ROUND_HALF_UP);

        new ADRectangle(dCode, _w, _w, null).write(this.out);
        return this;
    }

    /**
     * Defines a rectangular aperture.
     *
     * @param dCode The d code.
     * @param moWidth
     * @param moHeight
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics defineRectangle(int dCode, BigDecimal moWidth, BigDecimal moHeight) throws IOException {
        if (!this.writer.addDnn(dCode)) {
            throw new IOException(String.format("ADD%03d already exists", dCode));
        }
        endLast();
        new ADRectangle(
                dCode,
                moWidth.setScale(this.writer.fs().decDigi(), BigDecimal.ROUND_HALF_UP),
                moHeight.setScale(this.writer.fs().decDigi(), BigDecimal.ROUND_HALF_UP),
                null).write(this.out);
        return this;
    }

    /**
     * Defines a rectangular aperture.
     *
     * @param dCode The d code.
     * @param moWidth
     * @param moHeight
     * @param moHoleDiameter
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics defineRectangle(int dCode, BigDecimal moWidth, BigDecimal moHeight, BigDecimal moHoleDiameter) throws IOException {
        if (!this.writer.addDnn(dCode)) {
            throw new IOException(String.format("ADD%03d already exists", dCode));
        }
        endLast();
        new ADRectangle(
                dCode,
                moWidth.setScale(this.writer.fs().decDigi(), BigDecimal.ROUND_HALF_UP),
                moHeight.setScale(this.writer.fs().decDigi(), BigDecimal.ROUND_HALF_UP),
                moHoleDiameter.setScale(this.writer.fs().decDigi(), BigDecimal.ROUND_HALF_UP)).write(this.out);
        return this;
    }

    /**
     *
     * @param dCode
     * @param moWidth
     * @param moHeight
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics defineObound(int dCode, BigDecimal moWidth, BigDecimal moHeight) throws IOException {
        if (!this.writer.addDnn(dCode)) {
            throw new IOException(String.format("ADD%03d already exists", dCode));
        }
        endLast();
        new ADObound(
                dCode,
                moWidth.setScale(this.writer.fs().decDigi(), BigDecimal.ROUND_HALF_UP),
                moHeight.setScale(this.writer.fs().decDigi(), BigDecimal.ROUND_HALF_UP),
                null).write(this.out);
        return this;
    }

    /**
     *
     * @param dCode
     * @param moWidth
     * @param moHeight
     * @param moHoleDiameter
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics defineObound(int dCode, BigDecimal moWidth, BigDecimal moHeight, BigDecimal moHoleDiameter) throws IOException {
        if (!this.writer.addDnn(dCode)) {
            throw new IOException(String.format("ADD%03d already exists", dCode));
        }
        endLast();
        new ADObound(
                dCode,
                moWidth.setScale(this.writer.fs().decDigi(), BigDecimal.ROUND_HALF_UP),
                moHeight.setScale(this.writer.fs().decDigi(), BigDecimal.ROUND_HALF_UP),
                moHoleDiameter).write(this.out);
        return this;
    }

    /**
     * Defines a block aperture.
     *
     * @param dCode The d code.
     * @return New AB graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public BlockDefineGraphics defineBlock(int dCode) throws IOException {
        endLast();
        if (!this.writer.addDnn(dCode)) {
            return null;
        }
        BlockDefineGraphics gs = new BlockDefineGraphics(dCode, this.writer);
        this.lastGS = gs;
        return gs;
    }

    /**
     *
     * @param dark
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics loadPolarity(boolean dark) throws IOException {
        endLast();
        if (dark && !this.writer.isDark()) {
            // dark
            new LP("D").write(this.out);
        }
        else if (!dark && this.writer.isDark()) {
            // clear
            new LP("C").write(this.out);
        }
        this.writer.setDark(dark);
        return this;
    }

    /**
    *
    * @param dark
    * @return This graphics object.
    * @throws IOException Failed to write to the output stream.
    */
    public CommonGraphics loadPolarity(boolean dark, boolean always) throws IOException {
        endLast();
        if (always) {
            if (dark) {
                new LP("D").write(this.out);
            }
            else {
                // clear
                new LP("C").write(this.out);
            }
        }
        else {
            if (dark && !this.writer.isDark()) {
                // dark
                new LP("D").write(this.out);
            }
            else if (!dark && this.writer.isDark()) {
                // clear
                new LP("C").write(this.out);
            }
        }
        this.writer.setDark(dark);
        return this;
    }

    /**
     *
     * @param name The layer name.
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics layer(String name) throws IOException {
        new LNLayer(name).write(this.out);;
        return this;
    }

    /**
     *
     * @param dCode The d code
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics dnn(int dCode) throws IOException {
        endLast();
        if (!this.writer.contains(dCode)) {
            throw new IOException(String.format("D%03d not found", dCode));
        }
        new Dnn(dCode).write(this.out);
        return this;
    }

    /**
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics lineTo(Long fsX, Long fsY) throws IOException {
        endLast();
        if (this.lastState != 1) {
            new G01().write(this.out);
        }
        new D01Plot(vx(fsX), vy(fsY)).write(this.out);
        apply(fsX, fsY);
        this.lastState = 1;
        return this;
    }

    /**
     *
     * @param fsX coordination x with FS format.
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics lineToH(Long fsX) throws IOException {
        if (this.lastState != 1) {
            new G01().write(this.out);
        }
        new D01Plot(fsX, null).write(this.out);
        this.lastX = fsX;
        this.lastState = 1;
        return this;
    }

    /**
    *
    * @param fsY coordination y with FS format.
    * @return This graphics object.
    * @throws IOException Failed to write to the output stream.
    */
    public CommonGraphics lineToV(Long fsY) throws IOException {
        if (this.lastState != 1) {
            new G01().write(this.out);
        }
        new D01Plot(null, fsY).write(this.out);
        this.lastY = fsY;
        this.lastState = 1;
        return this;
    }

    /**
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @param fsI offset coordination i with FS format.
     * @param fsJ offset coordination j with FS format.
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics cwArc(Long fsX, Long fsY, Long fsI, Long fsJ) throws IOException {
        endLast();
        if (this.lastState == 1) {
            new G02().write(this.out);
        }
        else if (this.lastState == 3) {
            new G02().write(this.out);
        }
        new D01Plot(vx(fsX), vy(fsY), vx(fsI), vy(fsJ)).write(this.out);
        apply(fsX, fsY);
        this.lastState = 2;
        return this;
    }

    /**
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @param fsI offset coordination i with FS format.
     * @param fsJ offset coordination j with FS format.
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics ccwArc(Long fsX, Long fsY, Long fsI, Long fsJ) throws IOException {
        endLast();
        if (this.lastState == 1) {
            new G03().write(this.out);
        }
        else if (this.lastState == 2) {
            new G03().write(this.out);
        }
        new D01Plot(vx(fsX), vy(fsY), vx(fsI), vy(fsJ)).write(this.out);
        apply(fsX, fsY);
        this.lastState = 3;
        return this;
    }

    /**
     * Moves an object to new coordination.
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics move(Long fsX, Long fsY) throws IOException {
        endLast();
        new D02Move(fsX, fsY).write(this.out);
        apply(fsX, fsY);
        return this;
    }

    /**
     * Moves an object to new coordination.
     *
     * @param fsX coordination x with FS format.
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics moveH(Long fsX) throws IOException {
        endLast();
        new D02Move(fsX, null).write(this.out);
        this.lastX = fsX;
        return this;
    }

    /**
     * Moves an object to new coordination.
     *
     * @param fsY coordination y with FS format.
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics moveV(Long fsY) throws IOException {
        endLast();
        new D02Move(null, fsY).write(this.out);
        this.lastY = fsY;
        return this;
    }

    /**
     * Flashes an object at a specific coordination.
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics flash(Long fsX, Long fsY) throws IOException {
        endLast();
        new D03Flash(vx(fsX), vy(fsY)).write(this.out);
        apply(fsX, fsY);
        return this;
    }

    /**
     * Flashes an object at a specific coordination using MO unit(mm or inch).
     *
     * @param moX coordination x with MO unit.
     * @param moY coordination y with MO unit.
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics flashMO(double moX, double moY) throws IOException {
        return flash(this.writer.xy(moX), this.writer.xy(moY));
    }

    public CommonGraphics flashZero() throws IOException {
        return flash(0L, 0L);
    }

    /**
     * Creates a rectangle.
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @param fsW coordination width with FS format.
     * @param fsH coordination height with FS format.
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics rect(long fsX, long fsY, long fsW, long fsH) throws IOException {
        return move(fsX, fsY)
                .lineToH(fsX + fsW)
                .lineToV(fsY + fsH)
                .lineToH(fsX)
                .lineToV(fsY);
    }

    /**
     * Creates a rectangle.
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @param fsW coordination width with FS format.
     * @param fsH coordination height with FS format.
     * @param dCode The d code.
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics rect(long fsX, long fsY, long fsW, long fsH, int dCode) throws IOException {
        return dnn(dCode)
                .move(fsX, fsY)
                .lineToH(fsX + fsW)
                .lineToV(fsY + fsH)
                .lineToH(fsX)
                .lineToV(fsY);
    }

    /**
     * Creates a rectangle.
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @param fsW coordination width with FS format.
     * @param fsH coordination height with FS format.
     * @param dCode The d code.
     * @param dark Dark or clear.
     * @return This graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public CommonGraphics rect(long fsX, long fsY, long fsW, long fsH, int dCode, boolean dark) throws IOException {
        return loadPolarity(dark)
                .dnn(dCode)
                .move(fsX, fsY)
                .lineToH(fsX + fsW)
                .lineToV(fsY + fsH)
                .lineToH(fsX)
                .lineToV(fsY);
    }

    public CommonGraphics mirror(MirrorType mt) throws IOException {
        new LM(mt).write(this.out);
        return this;
    }

    public CommonGraphics rotate(BigDecimal degree) throws IOException {
        new LR(degree).write(this.out);
        return this;
    }

    /**
     * Creates a text graphics object.
     *
     * @return A text graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public TextGraphics createText() throws IOException {
        return createText("Arial");
    }

    /**
     * Creates a text graphics object.
     *
     * @param fontName The font name. 'Dialog' or 'Arial'.
     * @return A text graphics object.
     * @throws IOException Failed to write to the output stream.
     */
    public TextGraphics createText(String fontName) throws IOException {
        endLast();
        TextGraphics gs = new TextGraphics(this, fontName);
        this.lastGS = gs;
        return gs;
    }

    /**
     * Creates a region graphics object.
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @return A region graphics graphics.
     * @throws IOException Failed to write to the output stream.
     */
    public RegionGraphics createRegion(long fsX, long fsY) throws IOException {
        endLast();
        RegionGraphics gs = new RegionGraphics(fsX, fsY, this.lastState, this.out);
        this.lastGS = gs;
        return gs;
    }

    public CommonGraphics write(GerberX2Statement stmt) throws IOException {
        stmt.write(this.out);
        return this;
    }

    /**
     * Create a step-repeat graphics object.
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @param fsX coordination offset x with FS format.
     * @param fsY coordination offset y with FS format.
     * @return
     * @throws IOException Failed to write to output stream.
     */
    @Deprecated
    public StepRepeatGraphics createStepRepeat(int fsX, int fsY, long fsI, long fsJ) throws IOException {
        endLast();
        StepRepeatGraphics gs = new StepRepeatGraphics(fsX, fsY, fsI, fsJ, this.writer, this.lastState);
        this.lastGS = gs;
        return gs;
    }

    @Override
    public int lastState() {
        return this.lastState;
    }

    @Override
    public void close() throws IOException {
        endLast();
        if (this.lastGS != null) {
            this.lastState = this.lastGS.lastState();
            this.lastGS.close();
            this.lastGS = null;
        }
        this.writer = null;
    }

    protected void endLast() throws IOException {
        if (this.lastGS != null) {
            this.lastState = this.lastGS.lastState();
            this.lastGS.close();
            this.lastGS = null;
        }
    }

    private void apply(Long fsX, Long fsY) {
        if (fsX != null) {
            this.lastX = fsX;
        }
        if (fsY != null) {
            this.lastY = fsY;
        }
    }

    private Long vx(Long fsX) {
        return this.lastX != null
                ? fsX == null || this.lastX.longValue() == fsX ? null : fsX
                : fsX;
    }

    private Long vy(Long fsY) {
        return this.lastY != null
                ? fsY == null || this.lastY.longValue() == fsY ? null : fsY
                : fsY;
    }

}

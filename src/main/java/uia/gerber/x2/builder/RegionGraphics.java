package uia.gerber.x2.builder;

import java.io.IOException;
import java.io.OutputStream;

import uia.gerber.x2.GerberX2FileWriter;
import uia.gerber.x2.model.D01Plot;
import uia.gerber.x2.model.D02Move;
import uia.gerber.x2.model.G01;
import uia.gerber.x2.model.G02;
import uia.gerber.x2.model.G03;

/**
 * Region(G36..G37) graphics.
 *
 * @author Kyle K. Lin
 *
 */
public class RegionGraphics implements GerberX2Graphics {

    private GerberX2FileWriter writer;

    private int state;

    private OutputStream out;

    /**
     * The constructor.
     * @param fsX the initial x with FS format.
     * @param fsY the initial y with FS format.
     * @param initState The initial state.
     * @param out The output stream.
     * @throws IOException Failed to write to the output stream.
     */
    public RegionGraphics(GerberX2FileWriter writer, long fsX, long fsY, int initState, OutputStream out) throws IOException {
        this.writer = writer;
        this.out = out;
        this.state = initState;
        out.write("G36*\n".getBytes());
        new D02Move(fsX, fsY).write(out);
        this.writer.apply(fsX, fsY);
    }

    /**
     * moves to new position.
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @return This object.
     * @throws IOException Failed to write to the output stream.
     */
    public RegionGraphics move(Long fsX, Long fsY) throws IOException {
        new D02Move(fsX, fsY).write(this.out);
        this.writer.apply(fsX, fsY);
        return this;
    }

    /**
     * moves horizontally.
     *
     * @param fsX coordination x with FS format.
     * @return This object.
     * @throws IOException Failed to write to the output stream.
     */
    public RegionGraphics moveH(Long fsX) throws IOException {
        new D02Move(fsX, null).write(this.out);
        this.writer.apply(fsX, null);
        return this;
    }

    /**
     * moves vertically.
     *
     * @param fsY coordination y with FS format.
     * @return This object.
     * @throws IOException Failed to write to the output stream.
     */
    public RegionGraphics moveV(Long fsY) throws IOException {
        new D02Move(null, fsY).write(this.out);
        this.writer.apply(null, fsY);
        return this;
    }

    /**
     * draws a line to a new position.
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @return This object.
     * @throws IOException Failed to write to the output stream.
     */
    public RegionGraphics lineTo(Long fsX, Long fsY) throws IOException {
        if (this.state != 1) {
            new G01().write(this.out);
        }
        new D01Plot(this.writer.vx(fsX), this.writer.vy(fsY)).write(this.out);
        this.state = 1;
        this.writer.apply(fsX, fsY);
        return this;
    }

    /**
     * draws a horizontal line.
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @return This object.
     * @throws IOException Failed to write to the output stream.
     */
    public RegionGraphics lineToH(Long fsX) throws IOException {
        if (this.state != 1) {
            new G01().write(this.out);
        }
        new D01Plot(fsX, null).write(this.out);
        this.state = 1;
        this.writer.apply(fsX, null);
        return this;
    }

    /**
     * draws a vertical line.
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @return This object.
     * @throws IOException Failed to write to the output stream.
     */
    public RegionGraphics lineToV(Long fsY) throws IOException {
        if (this.state != 1) {
            new G01().write(this.out);
        }
        new D01Plot(null, fsY).write(this.out);
        this.state = 1;
        this.writer.apply(null, fsY);
        return this;
    }

    /**
     * draws a clockwise curve to a new position.
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @param fsCX center x with FS format.
     * @param fsCY center y with FS format.
     * @return This object.
     * @throws IOException Failed to write to the output stream.
     */
    public RegionGraphics cwTo(Long fsX, Long fsY, long fsCX, long fsCY) throws IOException {
        if (this.state != 2) {
            new G02().write(this.out);
        }
        new D01Plot(this.writer.vx(fsX), this.writer.vy(fsY), fsCX - this.writer.x(), fsCY - this.writer.y()).write(this.out);
        this.state = 2;
        this.writer.apply(fsX, fsY);
        return this;
    }

    /**
     * Draw a counterclockwise curve to a new position.
     *
     * @param fsX coordination x with FS format.
     * @param fsY coordination y with FS format.
     * @param fsCX center x with FS format.
     * @param fsCY center y with FS format.
     * @return This object.
     * @throws IOException Failed to write to the output stream.
     */
    public RegionGraphics ccwTo(Long fsX, Long fsY, long fsCX, long fsCY) throws IOException {
        if (this.state != 3) {
            new G03().write(this.out);
        }
        new D01Plot(this.writer.vx(fsX), this.writer.vy(fsY), fsCX - this.writer.x(), fsCY - this.writer.y()).write(this.out);
        this.state = 3;
        this.writer.apply(fsX, fsY);
        return this;
    }

    @Override
    public int lastState() {
        return this.state;
    }

    @Override
    public void close() throws IOException {
        if (this.out != null) {
            this.out.write("G37*\n".getBytes());
            this.out = null;
        }
    }
}

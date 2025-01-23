package uia.gerber.x2.builder;

import java.io.IOException;
import java.io.OutputStream;

import uia.gerber.x2.model.D01Plot;
import uia.gerber.x2.model.D02Move;
import uia.gerber.x2.model.G36Region.Contour;

/**
 * Region(G36..G37) graphics.
 *
 * @author Kyle K. Lin
 *
 */
public class RegionGraphics implements GerberX2Graphics {

    private int state;

    private OutputStream out;

    private long lastX;

    private long lastY;

    /**
     * The constructor.
     * @param fsX the initial x with FS format.
     * @param fsY the initial y with FS format.
     * @param initState The initial state.
     * @param out The output stream.
     * @throws IOException Failed to write to the output stream.
     */
    public RegionGraphics(long fsX, long fsY, int initState, OutputStream out) throws IOException {
        this.out = out;
        this.state = initState;
        out.write("G36*\n".getBytes());
        new D02Move(fsX, fsY).write(out);
        this.lastX = fsX;
        this.lastY = fsY;
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
        this.lastX = fsX;
        this.lastY = fsY;
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
        this.lastX = fsX;
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
        this.lastY = fsY;
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
            new Contour(Contour.G01).write(this.out);
        }
        new Contour(new D01Plot(vx(fsX), vy(fsY))).write(this.out);
        this.state = 1;
        apply(fsX, fsY);
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
            new Contour(Contour.G01).write(this.out);
        }
        new Contour(new D01Plot(fsX, null)).write(this.out);
        this.state = 1;
        this.lastX = fsX;
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
            new Contour(Contour.G01).write(this.out);
        }
        new Contour(new D01Plot(null, fsY)).write(this.out);
        this.state = 1;
        this.lastY = fsY;
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
            new Contour(Contour.G02).write(this.out);
        }
        new Contour(new D01Plot(vx(fsX), vy(fsY), vx(fsCX - this.lastX), vy(fsCY - this.lastY))).write(this.out);
        this.state = 2;
        apply(fsX, fsY);
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
            new Contour(Contour.G03).write(this.out);
        }
        new Contour(new D01Plot(vx(fsX), vy(fsY), vx(fsCX - this.lastX), vy(fsCY - this.lastY))).write(this.out);
        this.state = 3;
        apply(fsX, fsY);
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

    private void apply(Long fsX, Long fsY) {
        if (fsX != null) {
            this.lastX = fsX;
        }
        if (fsY != null) {
            this.lastY = fsY;
        }
    }

    private Long vx(Long fsX) {
        return fsX == null || this.lastX == fsX ? null : fsX;
    }

    private Long vy(Long fsY) {
        return fsY == null || this.lastY == fsY ? null : fsY;
    }
}

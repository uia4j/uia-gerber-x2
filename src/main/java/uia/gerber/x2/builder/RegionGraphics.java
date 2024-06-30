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

    public RegionGraphics(long x, long y, int initState, OutputStream out) throws IOException {
        this.out = out;
        this.state = initState;
        out.write("G36*\n".getBytes());
        new D02Move(x, y).write(out);
    }

    public RegionGraphics lineTo(Long x, Long y) throws IOException {
        if (this.state != 1) {
            new Contour("G01").write(this.out);
        }
        new Contour(new D01Plot(x, y)).write(this.out);
        this.state = 1;
        return this;
    }

    public RegionGraphics cwTo(Long x, Long y, Long i, Long j) throws IOException {
        if (this.state != 2) {
            new Contour("G02").write(this.out);
        }
        new Contour(new D01Plot(x, y, i, j)).write(this.out);
        this.state = 2;
        return this;
    }

    public RegionGraphics ccwTo(Long x, Long y, Long i, Long j) throws IOException {
        if (this.state != 3) {
            new Contour("G03").write(this.out);
        }
        new Contour(new D01Plot(x, y, i, j));
        this.state = 3;
        return this;
    }

    @Override
    public int lastState() {
        return this.state;
    }

    @Override
    public void close() throws IOException {
        this.out.write("G37*\n".getBytes());
        this.out = null;
    }
}

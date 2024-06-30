package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import uia.gerber.x2.GerberX2Statement;
import uia.gerber.x2.Valuer;

/**
 * FS
 *
 * @author Kyle K. Lin
 *
 */
public class FS implements GerberX2Statement {

    private Valuer x;

    private Valuer y;

    public FS(int xIntDigi, int xDecDigi, int yIntDigi, int yDecDigi) {
        this.x = new Valuer(xIntDigi, xDecDigi);
        this.y = new Valuer(yIntDigi, yDecDigi);
    }

    public FS(Valuer x, Valuer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String getCmd() {
        return "FS";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("%%FSLAX%sY%s*%%\n", this.x.digi(), this.y.digi()).getBytes());
    }

}

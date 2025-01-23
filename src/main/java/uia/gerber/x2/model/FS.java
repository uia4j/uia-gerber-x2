package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import uia.gerber.x2.GerberX2Statement;
import uia.gerber.x2.Valuer;

/**
 * FS(Format Specification) specifies the number of integer and decimal places to expect in the coordinate data.
 *
 * @author Kyle K. Lin
 *
 */
public class FS implements GerberX2Statement {

    private final Valuer valuer;

    public FS(int intDigi, int decDigi) {
        this.valuer = new Valuer(intDigi, decDigi);
    }

    public FS(Valuer valuer) {
        this.valuer = valuer;
    }

    @Override
    public String getCmd() {
        return "FS";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("%%FSLAX%sY%s*%%\n", this.valuer.coordDigits(), this.valuer.coordDigits()).getBytes());
    }

}

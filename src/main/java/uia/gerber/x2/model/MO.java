package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import uia.gerber.x2.GerberX2Statement;

/**
 * MO(Mode) sets the file unit to either millimeter or inch.
 *
 * @author Kyle K. Lin
 *
 */
public class MO implements GerberX2Statement {

    public enum UnitType {
        MM, IN
    }

    private UnitType unit;

    public MO() {
    }

    public MO(UnitType unit) {
        this.unit = unit;
    }

    @Override
    public String getCmd() {
        return "MO";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(("%MO" + this.unit + "*%\n").getBytes());
    }
}

package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import uia.gerber.x2.GerberX2Statement;

/**
 * LN(Layer Name)
 *
 * @author Kyle K. Lin
 *
 */
public class LN implements GerberX2Statement {

    private final String name;

    public LN(String name) {
        this.name = name;
    }

    @Override
    public String getCmd() {
        return "G04";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("%%LN%s*%%\n", this.name == null ? "" : this.name).getBytes());
    }
}

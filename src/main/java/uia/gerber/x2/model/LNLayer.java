package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import uia.gerber.x2.GerberX2Statement;

/**
 * G04
 *
 * @author Kyle K. Lin
 *
 */
public class LNLayer implements GerberX2Statement {

    private final String name;

    public LNLayer(String name) {
        this.name = name;
    }

    @Override
    public String getCmd() {
        return "LN";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("%%LN%s*%%\n", this.name).getBytes());
    }
}

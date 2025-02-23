package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import uia.gerber.x2.GerberX2Statement;

/**
 * AB(Block Aperture) creates a block aperture.
 *
 * @author Kyle K. Lin
 *
 */
public class ABBlock implements GerberX2Statement {

    private final String name;

    public final List<GerberX2Statement> stmts;

    public ABBlock(String name) {
        this.name = name;
        this.stmts = new ArrayList<>();
    }

    @Override
    public String getCmd() {
        return "AB";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("%%AB%S*%%\n", this.name).getBytes());
        for (GerberX2Statement stmt : this.stmts) {
            stmt.write(out);
        }
        out.write("%AB*%\n".getBytes());
    }
}

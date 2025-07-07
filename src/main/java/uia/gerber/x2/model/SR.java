package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import uia.gerber.x2.GerberX2Statement;

/**
 * SR
 *
 * @author Kyle K. Lin
 *
 */
public class SR implements GerberX2Statement {

    private int x;

    private int y;

    private long i;

    private long j;

    public final List<GerberX2Statement> stmts;

    public SR(int x, int y, long i, long j) {
        this.x = x;
        this.y = y;
        this.i = i;
        this.j = j;
        this.stmts = new ArrayList<>();
    }

    @Override
    public String getCmd() {
        return "SR";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("%%SRX%sY%sI%sJ%s*%%\n",
                this.x,
                this.y,
                this.i,
                this.j).getBytes());
        for (GerberX2Statement stmt : this.stmts) {
            stmt.write(out);
        }
        out.write("%SR*%\n".getBytes());
    }
}

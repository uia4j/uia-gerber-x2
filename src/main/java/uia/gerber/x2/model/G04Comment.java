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
public class G04Comment implements GerberX2Statement {

    private final String comment;

    public G04Comment(String comment) {
        this.comment = comment;
    }

    @Override
    public String getCmd() {
        return "G04";
    }

    public String getComment() {
        return this.comment;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("G04%s*\n", this.comment == null ? "" : this.comment).getBytes());
    }

    @Override
    public String toString() {
        return String.format("G04%s", this.comment == null ? "" : this.comment);
    }
}

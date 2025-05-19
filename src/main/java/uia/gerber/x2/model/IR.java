package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import uia.gerber.x2.GerberX2Statement;

/**
 * IR(Image Rotation). The IR command has been deprecated since revision I1 from December 2012.
 *
 * @author Kyle K. Lin
 *
 */
@Deprecated
public class IR implements GerberX2Statement {

    private final int degree;

    public IR() {
        this.degree = 0;
    }

    public IR(int degree) {
        this.degree = degree;
    }

    @Override
    public String getCmd() {
        return "IR";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("%%IR%s*%%\n", this.degree).getBytes());
    }

}

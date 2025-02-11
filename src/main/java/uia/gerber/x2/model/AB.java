package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * AB(Block Aperture) creates a block aperture.
 *
 * @author Kyle K. Lin
 *
 */
public class AB implements IAD {

    private final String dnn;

    public AB(String dnn) {
        this.dnn = dnn;
    }

    @Override
    public String getCmd() {
        return "AB";
    }

    @Override
    public String getDnn() {
        return this.dnn;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("%%AB%S*%%\n", this.dnn).getBytes());
    }
}

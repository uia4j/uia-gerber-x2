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

    private final int no;

    private final String dnn;

    public AB(String dnn) {
        this.no = Integer.parseInt(dnn.substring(1));
        this.dnn = dnn;
    }

    @Override
    public String getCmd() {
        return "AB";
    }

    @Override
    public int getNo() {
        return this.no;
    }

    @Override
    public String getDnn() {
        return this.dnn;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("%%AB%S*%%\n", this.dnn).getBytes());
    }

    @Override
    public String toString() {
        return String.format("AB%s", this.dnn);
    }
}

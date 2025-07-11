package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Kyle K. Lin
 *
 */
public class M02 implements IState {

    @Override
    public String getCmd() {
        return "M02";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write("M02*\n".getBytes());
    }

    @Override
    public String toString() {
        return "M02";
    }

}

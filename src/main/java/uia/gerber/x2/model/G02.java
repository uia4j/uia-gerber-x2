package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * G02(Circular Plotting) sets clockwise circular plot mode.
 *
 * @author Kyle K. Lin
 *
 */
public class G02 implements IState {

    @Override
    public String getCmd() {
        return "G02";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write("G02*\n".getBytes());
    }

}

package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * G03(Circular Plotting) sets counterclockwise circular plot mode.
 *
 * @author Kyle K. Lin
 *
 */
public class G03 implements IState {

    @Override
    public String getCmd() {
        return "G03";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write("G03*\n".getBytes());
    }

}

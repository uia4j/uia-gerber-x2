package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * G01(Linear Plotting) sets linear plot mode.
 *
 * @author Kyle K. Lin
 *
 */
public class G01 implements IState {

    @Override
    public String getCmd() {
        return "G01";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write("G01*\n".getBytes());
    }

}

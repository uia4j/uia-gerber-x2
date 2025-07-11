package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Kyle K. Lin
 *
 */
public class G37 implements IState {

    @Override
    public String getCmd() {
        return "G37";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write("G37*\n".getBytes());
    }

    @Override
    public String toString() {
        return "G37";
    }

}

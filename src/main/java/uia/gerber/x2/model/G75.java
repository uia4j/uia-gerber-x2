package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * G75. turns on 360° circular<br />
 * For compatibility with older versions of the Gerber format, a G75 must be issued before the first D01 in circular mode.
 *
 * @author Kyle K. Lin
 *
 */
public class G75 implements IState {

    @Override
    public String getCmd() {
        return "G75";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write("G75*\n".getBytes());
    }

}

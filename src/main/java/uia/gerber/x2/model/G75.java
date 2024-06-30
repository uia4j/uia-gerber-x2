package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * G75
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

package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Kyle K. Lin
 *
 */
public class G36 implements IState {

    @Override
    public String getCmd() {
        return "G36";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write("G36*\n".getBytes());
    }

}

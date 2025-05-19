package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * G02(Circular Plotting) sets clockwise circular plot mode.
 *
 * @author Kyle K. Lin
 *
 */
public class G02 implements IState, IG36Stmt {

    private boolean std;

    public G02() {
        this.std = true;
    }

    public G02(boolean std) {
        this.std = std;
    }

    @Override
    public String getCmd() {
        return "G02";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        if (this.std) {
            out.write("G02*\n".getBytes());
        }
        else {
            out.write("G02".getBytes());
        }
    }

}

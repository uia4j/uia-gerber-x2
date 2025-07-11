package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * G03(Circular Plotting) sets counterclockwise circular plot mode.
 *
 * @author Kyle K. Lin
 *
 */
public class G03 implements IPlot, IG36Stmt {

    private boolean std;

    public G03() {
        this.std = true;
    }

    public G03(boolean std) {
        this.std = std;
    }

    @Override
    public String getCmd() {
        return "G03";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        if (this.std) {
            out.write("G03*\n".getBytes());
        }
        else {
            out.write("G03".getBytes());
        }
    }

    @Override
    public String toString() {
        return "G03";
    }

}

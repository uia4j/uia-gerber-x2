package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import uia.gerber.x2.GerberX2Statement;

/**
 * G01(Linear Plotting) sets linear plot mode.
 *
 * @author Kyle K. Lin
 *
 */
public class G01 implements IState, IG36Stmt {

    private boolean std;

    public G01() {
        this.std = true;
    }

    public G01(boolean std) {
        this.std = std;
    }

    public static GerberX2Statement parse(String cmd) {
        return cmd.startsWith("G01") ? new G01() : null;
    }

    @Override
    public String getCmd() {
        return "G01";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        if (this.std) {
            out.write("G01*\n".getBytes());
        }
        else {
            out.write("G01".getBytes());
        }
    }

}

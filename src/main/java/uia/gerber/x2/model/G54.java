package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * G54(Tool Prepare) precedes an aperture D-code.
 *
 * @author Kyle K. Lin
 *
 */
public class G54 implements IState {

    private final int dCode;

    public G54(int dCode) {
        this.dCode = dCode;
    }

    @Override
    public String getCmd() {
        return "G54";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("G54D%03d*\n", this.dCode).getBytes());
    }

}

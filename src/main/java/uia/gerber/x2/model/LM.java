package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * LM
 *
 * @author Kyle K. Lin
 *
 */
public class LM implements ILoad {

    public enum MrriorType {
        N, X, Y, XY
    }

    private MrriorType mt;

    public LM(MrriorType mt) {
        this.mt = mt;
    }

    @Override
    public String getCmd() {
        return "LM";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(("%LM" + this.mt + "*%\n").getBytes());
    }
}
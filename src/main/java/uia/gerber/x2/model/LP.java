package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * LP
 *
 * @author Kyle K. Lin
 *
 */
public class LP implements ILoad {

    private String polarity;

    public LP() {
        this.polarity = "D";
    }

    public LP(String polarity) {
        this.polarity = polarity;
    }

    public LP(boolean polarity) {
        this.polarity = polarity ? "D" : "C";
    }

    @Override
    public String getCmd() {
        return "LP";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(("%LP" + this.polarity + "*%\n").getBytes());
    }
}

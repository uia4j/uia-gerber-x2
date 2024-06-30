package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

/**
 * LR
 *
 * @author Kyle K. Lin
 *
 */
public class LR implements ILoad {

    private BigDecimal degree;

    public LR(BigDecimal degree) {
        this.degree = degree;
    }

    @Override
    public String getCmd() {
        return "LR";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(("%LR" + this.degree + "*%\n").getBytes());
    }
}

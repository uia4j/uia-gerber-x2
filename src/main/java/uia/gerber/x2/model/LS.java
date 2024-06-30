package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

/**
 * LS
 *
 * @author Kyle K. Lin
 *
 */
public class LS implements ILoad {

    private BigDecimal scale;

    public LS(BigDecimal scale) {
        this.scale = scale;
    }

    @Override
    public String getCmd() {
        return "LS";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(("%LS" + this.scale + "*%\n").getBytes());
    }
}

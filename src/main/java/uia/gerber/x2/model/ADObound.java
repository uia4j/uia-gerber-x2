package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

/**
 * AD: Aperture Definition (Obound).
 *
 * @author Kyle K. Lin
 *
 */
public class ADObound extends AD {

    private final BigDecimal width;

    private final BigDecimal height;

    private final BigDecimal holeDiameter;

    public ADObound(String dnn, BigDecimal width, BigDecimal height, BigDecimal holeDiameter) {
        super(dnn, "O");
        this.width = width;
        this.height = height;
        this.holeDiameter = holeDiameter;
    }

    public ADObound(int no, BigDecimal width, BigDecimal height, BigDecimal holeDiameter) {
        super(no, "O");
        this.width = width;
        this.height = height;
        this.holeDiameter = holeDiameter;
    }

    public BigDecimal getWidth() {
        return this.width;
    }

    public BigDecimal getHeight() {
        return this.height;
    }

    public BigDecimal getHoleDiameter() {
        return this.holeDiameter;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        for (ATTR attr : getAttributes()) {
            attr.write(out);
        }
        out.write(String.format("%%AD%SO,%sX%s", getDnn(), this.width, this.height).getBytes());
        if (this.holeDiameter != null) {
            out.write(String.format("X%s*%%\n", this.holeDiameter).getBytes());
        }
        else {
            out.write("*%\n".getBytes());
        }
    }

}

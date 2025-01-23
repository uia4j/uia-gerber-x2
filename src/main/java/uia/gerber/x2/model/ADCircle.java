package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

/**
 * AD: Aperture Definition (Circle).
 *
 * @author Kyle K. Lin
 *
 */
public class ADCircle extends AD {

    private final BigDecimal diameter;

    private final BigDecimal holeDiameter;

    /**
     * The constructor.
     *
     * @param diameter The diameter.
     * @param holeDiameter The hole diameter.
     */
    public ADCircle(int no, BigDecimal diameter, BigDecimal holeDiameter) {
        super(no, "C");
        this.diameter = diameter;
        this.holeDiameter = holeDiameter;
    }

    public BigDecimal getDiameter() {
        return this.diameter;
    }

    public BigDecimal getHoleDiameter() {
        return this.holeDiameter;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        for (ATTR attr : getAttributes()) {
            attr.write(out);
        }
        out.write(String.format("%%AD%SC,%s", getDnn(), this.diameter).getBytes());
        if (this.holeDiameter != null) {
            out.write(String.format("X%s*%%\n", this.holeDiameter).getBytes());
        }
        else {
            out.write("*%\n".getBytes());
        }
    }

}

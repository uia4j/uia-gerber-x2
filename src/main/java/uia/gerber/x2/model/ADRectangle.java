package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

/**
 * AD: Aperture Definition (Rectangle).
 *
 * @author Kyle K. Lin
 *
 */
public class ADRectangle extends AD {

    private BigDecimal width;

    private BigDecimal height;

    private BigDecimal holeDiameter;

    /**
     * The constructor.
     *
     * @param no The number between 10..999.
     * @param width The width.
     * @param height The height.
     * @param holeDiameter The hole diameter.
     */
    public ADRectangle(int no, BigDecimal width, BigDecimal height, BigDecimal holeDiameter) {
        super(no, "R");
        if (no < 10 || no > 999) {
            throw new IllegalArgumentException("number out of range [10..999]");
        }
        this.width = width;
        this.height = height;
        this.holeDiameter = holeDiameter;
    }

    public BigDecimal getWidth() {
        return this.width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return this.height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getHoleDiameter() {
        return this.holeDiameter;
    }

    public void setHoleDiameter(BigDecimal holeDiameter) {
        this.holeDiameter = holeDiameter;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        for (ATTR attr : getAttributes()) {
            attr.write(out);
        }
        out.write(String.format("%%AD%SR,%sX%s", getDnn(), this.width, this.height).getBytes());
        if (this.holeDiameter != null) {
            out.write(String.format("X%s*%%\n", this.holeDiameter).getBytes());
        }
        else {
            out.write("*%\n".getBytes());
        }
    }

}

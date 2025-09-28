package uia.gerber.x2.am;

import java.math.BigDecimal;

/**
 *
 *
 * Last subsequent X and Y coordinates. As the outline must be closed the last coordinates must be equal to the start coordinates.
 *
 */
public class PrimitiveOutline implements Primitive {

    private boolean exposure;

    private int count;

    private BigDecimal[] xs;

    private BigDecimal[] ys;

    private BigDecimal roate;

    public PrimitiveOutline(String cmd, MacroValues values) throws Exception {
        String[] args = cmd.split(",");
        this.exposure = "1".equals(args[1]);
        this.count = Integer.parseInt(args[2]);
        this.xs = new BigDecimal[this.count + 1];
        this.ys = new BigDecimal[this.count + 1];
        for (int i = 0; i <= this.count; i++) {
            String x = args[3 + 2 * i];
            String y = args[4 + 2 * i];
            this.xs[i] = values.num(x);
            this.ys[i] = values.num(y);
        }
        this.roate = new BigDecimal(args[args.length - 1]);
    }

    @Override
    public Code getCode() {
        return Code.outline;
    }

    public boolean isExposure() {
        return this.exposure;
    }

    public int getCount() {
        return this.count;
    }

    public BigDecimal[] getXs() {
        return this.xs;
    }

    public BigDecimal[] getYs() {
        return this.ys;
    }

    public BigDecimal getRoate() {
        return this.roate;
    }
}

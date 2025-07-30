package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import uia.gerber.x2.Valuer;

/**
 * D02(Operation) moves the current point to the operation coordinates. No graphical object is generated.
 *
 * @author Kyle K. Lin
 *
 */
public class D02Move implements IOp {

    private String g;

    private Long x;

    private Long y;

    /**
     * The OP constructor.
     *
     */
    public D02Move() {
    }

    /**
     * The OP constructor.
     *
     * @param fsX
     * @param fsY
     */
    public D02Move(Long fsX, Long fsY) {
        this.x = fsX;
        this.y = fsY;
    }

    @Override
    public void scale(Valuer from, Valuer to) {
        this.x = to.out(from.in(this.x));
        this.y = to.out(from.in(this.y));
    }

    @Override
    public String getCmd() {
        return "D2";
    }

    @Override
    public Long getX() {
        return this.x;
    }

    public void setX(Long fsX) {
        this.x = fsX;
    }

    @Override
    public Long getY() {
        return this.y;
    }

    public void setY(Long fsY) {
        this.y = fsY;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        if (this.g != null) {
            out.write(this.g.getBytes());
        }
        if (this.x != null) {
            out.write(String.format("X%s", this.x).getBytes());
        }
        if (this.y != null) {
            out.write(String.format("Y%s", this.y).getBytes());
        }
        out.write("D02*\n".getBytes());
    }

    @Override
    public String toString() {
        return String.format("D02(%s,%s)", this.x, this.y);
    }
}

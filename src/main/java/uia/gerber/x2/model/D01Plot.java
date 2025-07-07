package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import uia.gerber.x2.Valuer;

/**
 * D01 creates a straight-line segment or a circular segment by plotting from the current point to the operation coordinates.
 *
 * @author Kyle K. Lin
 *
 */
public class D01Plot implements IOp, IG36Stmt {

    private String g;

    private Long x;

    private Long y;

    private Long i;

    private Long j;

    public D01Plot() {
    }

    /**
     * The plotting constructor.
     *
     * @param fsX
     * @param fsY
     *
     */
    public D01Plot(Long fsX, Long fsY) {
        this.x = fsX;
        this.y = fsY;
    }

    /**
     * The plotting constructor.
     *
     * @param fsX
     * @param fsY
     * @param fsI
     * @param fsJ
     */
    public D01Plot(Long fsX, Long fsY, Long fsI, Long fsJ) {
        this.x = fsX;
        this.y = fsY;
        this.i = fsI;
        this.j = fsJ;
    }

    /**
     * The plotting constructor.
     *
     * @param g
     * @param x
     * @param y
     * @param i
     * @param j
     */
    public D01Plot(String g, Long fsX, Long fsY, Long fsI, Long fsJ) {
        this.g = g;
        this.x = fsX;
        this.y = fsY;
        this.i = fsI;
        this.j = fsJ;
    }

    @Override
    public String getCmd() {
        return "D01";
    }

    public Long getX() {
        return this.x;
    }

    public void setX(Long fsX) {
        this.x = fsX;
    }

    public Long getY() {
        return this.y;
    }

    public void setY(Long fsY) {
        this.y = fsY;
    }

    public Long getI() {
        return this.i;
    }

    public void setI(Long i) {
        this.i = i;
    }

    public Long getJ() {
        return this.j;
    }

    public void setJ(Long j) {
        this.j = j;
    }

    @Override
    public void scale(Valuer from, Valuer to) {
        this.x = to.out(from.in(this.x));
        this.y = to.out(from.in(this.y));
        this.i = to.out(from.in(this.i));
        this.j = to.out(from.in(this.j));
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
        if (this.i != null) {
            out.write(String.format("I%s", this.i).getBytes());
        }
        if (this.j != null) {
            out.write(String.format("J%s", this.j).getBytes());
        }
        out.write("D01*\n".getBytes());
    }
}

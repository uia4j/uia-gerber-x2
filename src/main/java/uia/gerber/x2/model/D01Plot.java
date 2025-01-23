package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import org.antlr.v4.runtime.Token;

import uia.gerber.x2.TokenUtils;

/**
 * D01 creates a straight-line segment or a circular segment by plotting from the current point to the operation coordinates.
 *
 * @author Kyle K. Lin
 *
 */
public class D01Plot implements IOp {

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

    /**
     * The plotting constructor.
     *
     * @param g
     * @param x
     * @param y
     * @param i
     * @param j
     */
    public D01Plot(Token g, Token x, Token y, Token i, Token j) {
        this.g = g == null ? null : g.getText();
        this.x = x == null ? null : TokenUtils.parseLong(x);
        this.y = y == null ? null : TokenUtils.parseLong(y);
        this.i = i == null ? null : TokenUtils.parseLong(i);
        this.j = j == null ? null : TokenUtils.parseLong(j);
    }

    @Override
    public String getCmd() {
        return "D01";
    }

    public Long getX() {
        return this.x;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public Long getY() {
        return this.y;
    }

    public void setY(Long y) {
        this.y = y;
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

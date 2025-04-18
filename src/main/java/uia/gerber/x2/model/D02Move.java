package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import org.antlr.v4.runtime.Token;

import uia.gerber.x2.TokenUtils;
import uia.gerber.x2.Valuer;

/**
 * D02 moves the current point to the operation coordinates. No graphical object is generated.
 *
 * @author Kyle K. Lin
 *
 */
public class D02Move implements IOp {

    private String g;

    private Long x;

    private Long y;

    public D02Move() {
    }

    public D02Move(Long fsX, Long fsY) {
        this.x = fsX;
        this.y = fsY;
    }

    public D02Move(Token g, Token x, Token y) {
        this.g = g == null ? null : g.getText();
        this.x = x == null ? null : TokenUtils.parseLong(x);
        this.y = y == null ? null : TokenUtils.parseLong(y);
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
}

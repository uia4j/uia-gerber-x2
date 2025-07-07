package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import uia.gerber.x2.Valuer;

/**
 * D03 creates a flash object by replicating (flashing) the current aperture at the operation coordinates.
 *
 * @author Kyle K. Lin
 *
 */
public class D03Flash implements IOp {

    private Long x;

    private Long y;

    public D03Flash() {
    }

    public D03Flash(Long fsX, Long fsY) {
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
        return "D3";
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
        if (this.x != null) {
            out.write(String.format("X%s", this.x).getBytes());
        }
        if (this.y != null) {
            out.write(String.format("Y%s", this.y).getBytes());
        }
        out.write("D03*\n".getBytes());
    }
}

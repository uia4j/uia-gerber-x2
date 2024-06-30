package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import org.antlr.v4.runtime.Token;

import uia.gerber.x2.TokenUtils;

/**
 * D03
 *
 * @author Kyle K. Lin
 *
 */
public class D03Flash implements IOp {

    private Long x;

    private Long y;

    public D03Flash() {
    }

    public D03Flash(Long x, Long y) {
        this.x = x;
        this.y = y;
    }

    public D03Flash(Token x, Token y) {
        this.x = TokenUtils.parseLong(x);
        this.y = TokenUtils.parseLong(y);
    }

    @Override
    public String getCmd() {
        return "D3";
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

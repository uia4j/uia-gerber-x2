package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import uia.gerber.x2.GerberX2Statement;

/**
 * LN(Layer Name). Deprecated in 2013.
 *
 * @author Kyle K. Lin
 *
 */
@Deprecated
public class LNLayer implements GerberX2Statement {

    private final String name;

    private final Charset charset;

    public LNLayer(String name) {
        this.name = name;
        this.charset = Charset.defaultCharset();
    }

    public LNLayer(String name, Charset charset) {
        this.name = name;
        this.charset = charset;
    }

    @Override
    public String getCmd() {
        return "LN";
    }

    public String getName() {
        return this.name;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("%%LN%s*%%\n", this.name).getBytes(this.charset));
    }
}

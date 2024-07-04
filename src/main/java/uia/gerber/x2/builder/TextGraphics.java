package uia.gerber.x2.builder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import uia.gerber.x2.GerberX2Statement;
import uia.gerber.x2.font.ASCII;
import uia.gerber.x2.font.Arial;

/**
 * Region(G36..G37) graphics.
 *
 * @author Kyle K. Lin
 *
 */
public class TextGraphics implements GerberX2Graphics {

    private OutputStream out;

    public TextGraphics(OutputStream out) throws IOException {
        this.out = out;
    }

    public TextGraphics text(String text, long x, long y, int unit) throws IOException {
        for (ASCII ascii : Arial.text(text)) {
            List<GerberX2Statement> g36s = ascii.d36(x, y, unit);
            for (GerberX2Statement g36 : g36s) {
                g36.write(this.out);
            }
            x += (ascii.getWidth() * unit);
        }
        return this;
    }

    @Override
    public int lastState() {
        return 1;
    }

    @Override
    public void close() throws IOException {
        this.out = null;
    }
}

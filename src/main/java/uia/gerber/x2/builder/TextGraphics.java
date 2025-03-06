package uia.gerber.x2.builder;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import uia.gerber.x2.GerberX2Statement;
import uia.gerber.x2.font.ASCII;
import uia.gerber.x2.font.GFont;
import uia.gerber.x2.font.GFontFactory;

/**
 * Region(G36..G37) graphics.
 *
 * @author Kyle K. Lin
 *
 */
public class TextGraphics implements GerberX2Graphics {

    private OutputStream out;

    private final GFont font;

    public TextGraphics(OutputStream out) throws IOException {
        this(out, "Dialog");
    }

    public TextGraphics(OutputStream out, String fontName) throws IOException {
        this.out = out;
        this.font = GFontFactory.get(fontName);
    }

    public TextGraphics text(String text, long fsX, long fsY, long fsW, long fsH) throws IOException {
        for (ASCII ascii : this.font.text(text)) {
            long chW = (ascii.getWidth() * this.font.scale((int) fsH));
            if (fsX + chW > fsX + fsW) {
                break;
            }

            List<GerberX2Statement> g36s = ascii.g36(fsX, fsY, fsH);
            for (GerberX2Statement g36 : g36s) {
                g36.write(this.out);
            }
            fsX += chW;
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

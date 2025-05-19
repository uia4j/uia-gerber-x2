package uia.gerber.x2.builder;

import java.io.IOException;
import java.util.List;

import uia.gerber.x2.GerberX2Statement;
import uia.gerber.x2.font.ASCII;
import uia.gerber.x2.font.GFont;
import uia.gerber.x2.font.GFontFactory;
import uia.gerber.x2.model.LP;

/**
 * Region(G36..G37) graphics.
 *
 * @author Kyle K. Lin
 *
 */
public class TextGraphics implements GerberX2Graphics {

    private CommonGraphics cg;

    private GFont font;

    public TextGraphics(CommonGraphics cg) throws IOException {
        this(cg, "Dialog");
    }

    public TextGraphics(CommonGraphics cg, String fontName) throws IOException {
        this.cg = cg;
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
                g36.write(this.cg.out);
                if (g36 instanceof LP) {
                    this.cg.writer.setDark(((LP) g36).isDark());
                }
            }
            fsX += chW;
        }

        new LP(true).write(this.cg.out);
        this.cg.writer.setDark(true);

        return this;
    }

    @Override
    public int lastState() {
        return 1;
    }

    @Override
    public void close() throws IOException {
        this.cg = null;
    }
}

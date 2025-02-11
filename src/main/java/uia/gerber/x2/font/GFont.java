package uia.gerber.x2.font;

import java.util.List;

public abstract class GFont {

    public abstract ASCII ch(char ch);

    public abstract List<ASCII> text(String text);

    public abstract int getHeight();

    public abstract int getDescent();

    public int scale(int height) {
        return height / getHeight();
    }

}

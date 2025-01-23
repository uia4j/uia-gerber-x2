package uia.gerber.x2.font;

public final class GFontFactory {

    private GFontFactory() {
    }

    public static GFont get(String name) {
        if (name.equals("Arial")) {
            return new Arial();
        }
        if (name.equals("Dialog")) {
            return new Dialog();
        }
        return null;
    }
}

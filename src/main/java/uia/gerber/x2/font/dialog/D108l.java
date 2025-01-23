package uia.gerber.x2.font.dialog;

public final class D108l extends D {

    public static int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 1609, 1628, 5528, 5509, 1609 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 39;
    }
}

package uia.gerber.x2.font.arial;

public final class D045Dash extends D {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 3999, 4037, 4601, 4563, 3999 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 47;
    }
}

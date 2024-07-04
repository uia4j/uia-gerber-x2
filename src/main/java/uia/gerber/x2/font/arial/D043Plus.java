package uia.gerber.x2.font.arial;

public final class D043Plus extends D {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 3725, 3737, 5869, 5952, 5978, 6880, 6854, 6935, 9149, 9137, 6923, 6840, 6814, 5912, 5938, 5857, 3725 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 82;
    }
}

package uia.gerber.x2.font.arial;

public final class D072H extends D {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 2738, 2751, 6791, 6893, 6944, 6844, 2804, 2817, 12917, 12904, 8258, 8156, 8105, 8205, 12851, 12838, 2738 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 101;
    }
}

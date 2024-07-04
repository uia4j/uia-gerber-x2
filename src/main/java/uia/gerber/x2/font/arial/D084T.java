package uia.gerber.x2.font.arial;

public final class D084T extends D {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 2298, 2378, 3398, 3366, 3450, 10845, 10831, 3436, 3350, 3318, 2298 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 85;
    }
}

package uia.gerber.x2.font.arial;

public final class D076L extends D {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 2116, 2130, 8916, 8995, 9043, 9979, 9916, 2116 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 78;
    }
}

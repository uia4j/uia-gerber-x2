package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D069E extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 2522, 2595, 3711, 3652, 3744, 6348, 6442, 6497, 7613, 7558, 7650, 10626, 10720, 10781, 11897, 11822, 2522 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 93;
    }
}

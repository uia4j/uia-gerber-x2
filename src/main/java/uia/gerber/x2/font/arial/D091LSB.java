package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D091LSB extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 1063, 1090, 1480, 1466, 1504, 5638, 5678, 5692, 6082, 6055, 1063 };
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

package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D124 extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 888, 899, 5484, 5473, 888 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 35;
    }
}

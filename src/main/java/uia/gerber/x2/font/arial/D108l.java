package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D108l extends ASCII {

    public static int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 846, 858, 3958, 3946, 846 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 31;
    }
}

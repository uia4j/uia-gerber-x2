package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D073I extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 1066, 1079, 4979, 4966, 1066 };
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

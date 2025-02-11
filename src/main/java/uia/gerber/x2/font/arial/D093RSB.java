package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D093RSB extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 1056, 1083, 6075, 6048, 5658, 5672, 5634, 1500, 1460, 1446, 1056 };
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

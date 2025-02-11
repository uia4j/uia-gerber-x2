package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D061Equal extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[2][];
        paths[0] = new int[] { 0x31, 4682, 4748, 5650, 5584, 4682 };
        paths[1] = new int[] { 0x31, 7142, 7208, 8192, 8126, 7142 };
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

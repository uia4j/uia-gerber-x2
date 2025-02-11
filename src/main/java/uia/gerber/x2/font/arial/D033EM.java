package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D033EM extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[2][];
        paths[0] = new int[] { 0x31, 1065, 1080, 2250, 2288, 2951, 2989, 3652, 3690, 4002, 3994, 3760, 3720, 3252, 3212, 2705, 2665, 2197, 2157, 1065 };
        paths[1] = new int[] { 0x31, 4420, 4434, 4980, 4966, 4420 };
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

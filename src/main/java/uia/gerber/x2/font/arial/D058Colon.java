package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D058Colon extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[2][];
        paths[0] = new int[] { 0x31, 2119, 2133, 2679, 2665, 2119 };
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

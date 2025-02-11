package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D034DQ extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[2][];
        paths[0] = new int[] { 0x31, 1356, 1370, 2320, 2369, 2619, 2668, 2918, 2967, 3117, 3110, 3010, 2959, 2709, 2658, 2408, 2357, 2207, 2156, 1356 };
        paths[1] = new int[] { 0x31, 1379, 1393, 2343, 2392, 2642, 2691, 2941, 2990, 3140, 3132, 3032, 2981, 2731, 2680, 2430, 2379, 1379 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 50;
    }
}

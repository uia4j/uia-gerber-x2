package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D116t extends ASCII {

    public static int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 1154, 1155, 2091, 2131, 2142, 2532, 2521, 2559, 4431, 4471, 4511, 4551, 4560, 4638, 4678, 4834, 4874, 4952, 4990, 4988, 5026, 5017, 4977, 4973, 4933, 4932, 4892, 4852, 4812, 4772, 4732, 4654, 4614, 4263, 4223, 2546, 2506, 2498, 2108, 2116, 2078, 1415, 1416, 1378, 1379, 1341, 1303, 1304, 1266, 1267, 1229, 1191, 1154 };
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

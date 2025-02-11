package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D102f extends ASCII {

    public static int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 1002, 1013, 1481, 1441, 1437, 1475, 1472, 1510, 1548, 1586, 1624, 1780, 1818, 2091, 2131, 2144, 2534, 2521, 2559, 4977, 4965, 2547, 2507, 2497, 2107, 2117, 2079, 1611, 1573, 1417, 1379, 1340, 1302, 1264, 1225, 1187, 1149, 1150, 1112, 1074, 1075, 1037, 1002 };
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

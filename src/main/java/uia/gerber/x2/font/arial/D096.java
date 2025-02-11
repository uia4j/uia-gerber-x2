package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D096 extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 1229, 1245, 1292, 1340, 1387, 1435, 1482, 1530, 1577, 1625, 1672, 1720, 1767, 1815, 1862, 1910, 1957, 2005, 2052, 2100, 2147, 2137, 2089, 2041, 1993, 1946, 1898, 1850, 1802, 1754, 1706, 1659, 1611, 1563, 1515, 1467, 1419, 1372, 1324, 1276, 1229 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 47;
    }
}

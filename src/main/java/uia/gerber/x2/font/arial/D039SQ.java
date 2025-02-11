package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D039SQ extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 735, 749, 1262, 1288, 1423, 1449, 1584, 1610, 1691, 1683, 1629, 1601, 1466, 1438, 1303, 1275, 735 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 27;
    }
}

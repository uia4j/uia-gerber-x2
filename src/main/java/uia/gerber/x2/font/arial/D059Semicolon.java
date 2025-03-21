package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D059Semicolon extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[2][];
        paths[0] = new int[] { 0x31, 2118, 2132, 2678, 2664, 2118 };
        paths[1] = new int[] { 0x31, 4419, 4433, 5291, 5329, 5368, 5406, 5445, 5483, 5522, 5560, 5598, 5636, 5674, 5673, 5711, 5710, 5748, 5708, 5669, 5629, 5590, 5550, 5511, 5512, 5474, 5436, 5398, 5360, 5322, 5244, 5206, 5011, 4971, 4965, 4419 };
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

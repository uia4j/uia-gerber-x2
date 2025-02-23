package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D090Z extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 2331, 2402, 3434, 3519, 3690, 3775, 4116, 4201, 4627, 4712, 5053, 5138, 5479, 5564, 5990, 6075, 6416, 6501, 6842, 6927, 7353, 7438, 7779, 7864, 8205, 8290, 8716, 8801, 9142, 9227, 9653, 9738, 9823, 9910, 9972, 11004, 10925, 9807, 9722, 9551, 9466, 9125, 9040, 8614, 8529, 8188, 8103, 7762, 7677, 7336, 7251, 6825, 6740, 6399, 6314, 5973, 5888, 5547, 5462, 5036,
                4951, 4610, 4525, 4184, 4099, 3673, 3588, 3503, 3416, 3363, 2331 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 86;
    }
}

package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D114r extends ASCII {

    public static int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 2523, 2531, 2579, 2581, 2629, 2631, 3054, 3100, 3147, 3193, 3240, 3192, 3191, 3143, 3138, 3090, 3136, 3133, 3179, 3178, 3224, 3270, 3316, 3362, 3409, 3455, 3502, 3548, 3736, 3782, 4017, 4063, 5990, 5978, 2547, 2558, 2934, 2982, 2936, 2890, 2844, 2797, 2751, 2705, 2659, 2613, 2614, 2568, 2523 };
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

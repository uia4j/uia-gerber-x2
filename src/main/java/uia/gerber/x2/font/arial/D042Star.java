package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D042Star extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 1372, 1382, 1652, 1705, 2137, 2190, 2244, 2299, 2246, 2247, 2194, 2195, 2142, 2144, 2091, 2092, 2039, 2041, 1988, 1991, 2045, 2100, 2208, 2263, 2371, 2426, 2480, 2478, 2531, 2530, 2583, 2582, 2635, 2629, 2682, 2681, 2734, 2789, 2844, 2899, 2954, 3008, 3063, 3118, 3173, 3228, 3283, 3337, 3392, 3445, 3444, 3497, 3550, 3603, 3602, 3655, 3708, 3653, 3599, 3544,
                3489, 3435, 3380, 3326, 3271, 3217, 3162, 3107, 3052, 2998, 2943, 2996, 3049, 3103, 3156, 3209, 3263, 3316, 3370, 3423, 3477, 3530, 3583, 3637, 3690, 3635, 3580, 3579, 3524, 3469, 3414, 3413, 3358, 3305, 3252, 3198, 3145, 3092, 3039, 2985, 2932, 2879, 2826, 2773, 2720, 2666, 2611, 2607, 2552, 2549, 2494, 2491, 2436, 2434, 2380, 2327, 2219, 2166, 2058, 2005, 1951, 1953, 2008, 2011,
                2066, 2067, 2122, 2124, 2179, 2180, 2235, 2290, 2237, 1913, 1858, 1372 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 54;
    }
}

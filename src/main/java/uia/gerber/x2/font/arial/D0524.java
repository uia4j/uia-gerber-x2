package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D0524 extends ASCII {

    private static final int[][] paths;

    static {
        paths = new int[2][];
        paths[0] = new int[] { 0x31, 2153, 2164, 7156, 7235, 7247, 8105, 8093, 8170, 9964, 9951, 8157, 8078, 8036, 7100, 7023, 6945, 6868, 6791, 6714, 6636, 6559, 6482, 6404, 6327, 6250, 6172, 6095, 6018, 5941, 5863, 5786, 5709, 5631, 5554, 5477, 5399, 5322, 5245, 5168, 5090, 5013, 4936, 4858, 4781, 4704, 4627, 4549, 4472, 4395, 4317, 4240, 4163, 4085, 4008, 3931, 3854, 3776, 3699, 3622, 3544,
                3467, 3390, 3312, 3235, 3158, 3081, 3003, 2926, 2849, 2771, 2694, 2617, 2539, 2462, 2385, 2308, 2153 };
        paths[1] = new int[] { 0x32, 3711, 7221, 7190, 7113, 7035, 6958, 6881, 6803, 6726, 6649, 6572, 6494, 6417, 6340, 6262, 6185, 6108, 6030, 5953, 5876, 5798, 5721, 5644, 5566, 5489, 5412, 5335, 5257, 5180, 5103, 5025, 4948, 4871, 4793, 4716, 4639, 4561, 4484, 4407, 4329, 4252, 4175, 4098, 4020, 3943, 3866, 3711 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 78;
    }
}

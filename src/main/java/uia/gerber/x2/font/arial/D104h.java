package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D104h extends ASCII {

    public static int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 2115, 2128, 4702, 4781, 4704, 4627, 4550, 4551, 4474, 4397, 4398, 4321, 4323, 4246, 4248, 4171, 4184, 4263, 4264, 4343, 4345, 4424, 4425, 4504, 4505, 4584, 4663, 4664, 4743, 4822, 4900, 4979, 5057, 5136, 5215, 5449, 5528, 9974, 9962, 5984, 5905, 5671, 5592, 5514, 5435, 5356, 5277, 5198, 5119, 5118, 5039, 5035, 4956, 4953, 5030, 5027, 5104, 5103, 5180, 5179,
                5256, 5255, 5332, 5409, 5486, 5563, 5641, 5718, 5796, 5873, 6029, 6106, 9928, 9915, 2115 };
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

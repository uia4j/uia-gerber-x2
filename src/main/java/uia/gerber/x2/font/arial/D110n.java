package uia.gerber.x2.font.arial;

import uia.gerber.x2.font.ASCII;

public final class D110n extends ASCII {

    public static int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 4171, 4184, 4263, 4265, 4344, 4346, 4425, 4426, 4505, 4584, 4663, 4664, 4743, 4821, 4900, 4979, 5058, 5214, 5293, 5527, 5606, 9974, 9962, 5984, 5905, 5671, 5592, 5514, 5435, 5356, 5277, 5198, 5119, 5117, 5038, 5035, 4956, 4954, 5031, 5028, 5105, 5103, 5180, 5178, 5255, 5332, 5409, 5486, 5563, 5641, 5718, 5796, 5873, 6029, 6106, 9928, 9915, 4221, 4232, 4934,
                5013, 4936, 4859, 4781, 4704, 4627, 4550, 4551, 4474, 4475, 4398, 4321, 4323, 4246, 4171 };
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

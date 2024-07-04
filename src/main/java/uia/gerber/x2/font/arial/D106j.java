package uia.gerber.x2.font.arial;

public final class D106j extends D {

    public static int[][] paths;

    static {
        paths = new int[2][];
        paths[0] = new int[] { 0x31, 819, 831, 1251, 1239, 819 };
        paths[1] = new int[] { 0x31, 1629, 1641, 4281, 4310, 4400, 4429, 4459, 4488, 4518, 4547, 4576, 4605, 4634, 4633, 4662, 4661, 4690, 4680, 4380, 4383, 4354, 4355, 4326, 4297, 4268, 4238, 4209, 1629 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 30;
    }
}

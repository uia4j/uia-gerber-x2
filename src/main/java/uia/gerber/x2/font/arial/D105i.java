package uia.gerber.x2.font.arial;

public final class D105i extends D {

    public static int[][] paths;

    static {
        paths = new int[2][];
        paths[0] = new int[] { 0x31, 846, 859, 1293, 1280, 846 };
        paths[1] = new int[] { 0x31, 1683, 1696, 3959, 3946, 1683 };
    }

    @Override
    public int[][] getData() {
        return paths;
    }

    @Override
    public int getWidth() {
        return 31;
    }
}

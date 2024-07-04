package uia.gerber.x2.font.arial;

public final class D070F extends D {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 2333, 2401, 3433, 3380, 3465, 5959, 6046, 6092, 7038, 6992, 7077, 10947, 10933, 2333 };
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

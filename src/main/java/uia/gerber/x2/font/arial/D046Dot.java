package uia.gerber.x2.font.arial;

public final class D046Dot extends D {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 4420, 4434, 4980, 4966, 4420 };
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

package uia.gerber.x2.font.arial;

public final class D095UnderScore extends D {

    private static final int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 11388, 11465, 12167, 12090, 11388 };
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
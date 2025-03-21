package uia.gerber.x2.font.dialog;

import uia.gerber.x2.font.ASCII;

public final class D110n extends ASCII {

    public static int[][] paths;

    static {
        paths = new int[1][];
        paths[0] = new int[] { 0x31, 5810, 5820, 5907, 5909, 5996, 5998, 6085, 6086, 6173, 6260, 6347, 6348, 6435, 6521, 6608, 6695, 6781, 6868, 7040, 7127, 7385, 7472, 12202, 12183, 8055, 7968, 7710, 7623, 7537, 7450, 7363, 7276, 7189, 7102, 7098, 7011, 7096, 7092, 7177, 7176, 7261, 7260, 7345, 7430, 7515, 7600, 7686, 7771, 7857, 7942, 8372, 8457, 12155, 12136, 5858, 5876, 6650, 6737, 6652, 6567,
                6482, 6397, 6312, 6227, 6228, 6143, 6058, 6060, 5975, 5976, 5891, 5810 };
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

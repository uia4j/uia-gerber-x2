package uia.gerber.x2.font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import uia.gerber.x2.GerberX2Statement;
import uia.gerber.x2.model.D01Plot;
import uia.gerber.x2.model.D02Move;
import uia.gerber.x2.model.G36Region;
import uia.gerber.x2.model.G36Region.Contour;
import uia.gerber.x2.model.LP;

public abstract class ASCII {

    protected abstract int[][] getData();

    public abstract int getWidth();

    public abstract int getHeight();

    public void println() {
        int w = getWidth();
        int h = getHeight();

        byte[] map = new byte[w * h];
        Arrays.fill(map, (byte) 0x20);
        int[][] data = getData();
        for (int[] path : data) {
            int c = path[0];
            for (int i = 1; i < path.length; i++) {
                map[path[i]] = (byte) c;
            }
        }

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                System.out.print((char) map[y * w + x]);
            }
            System.out.println();
        }
    }

    public List<GerberX2Statement> g36(long left, long top, int unit) {
        int w = getWidth();
        List<GerberX2Statement> result = new ArrayList<>();
        int[][] data = getData();
        if (data.length == 0) {
            return Collections.emptyList();
        }

        for (int i = 0; i < data.length; i++) {
            int[] path = data[i];

            result.add(new LP(path[0] == 0x31));

            int p = path[1];
            long x = left + (p % w) * unit;
            long y = top - (p / w) * unit;
            G36Region g36 = new G36Region(new D02Move(x, y));
            for (int j = 2; j < path.length; j++) {
                p = path[j];
                long _x = left + (p % w) * unit;
                long _y = top - (p / w) * unit;
                g36.contours.add(new Contour(new D01Plot(_x == x ? null : _x, _y == y ? null : _y)));
                x = _x;
                y = _y;
            }
            result.add(g36);
        }
        return result;
    }
}

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

    public List<GerberX2Statement> g36(long fsX, long fsY, int scale) {
        fsY += (getHeight() * scale);
        int fsW = getWidth();
        List<GerberX2Statement> result = new ArrayList<>();
        int[][] data = getData();
        if (data.length == 0) {
            return Collections.emptyList();
        }

        for (int i = 0; i < data.length; i++) {
            int[] path = data[i];

            // dark or clear
            result.add(new LP(path[0] == 0x31));

            int p = path[1];
            long _fsX = fsX + (p % fsW) * scale;
            long _fsY = fsY - (p / fsW) * scale;
            G36Region g36 = new G36Region(new D02Move(_fsX, _fsY));
            for (int j = 2; j < path.length; j++) {
                p = path[j];
                long _x = fsX + (p % fsW) * scale;
                long _y = fsY - (p / fsW) * scale;
                g36.contours.add(new Contour(new D01Plot(_x == _fsX ? null : _x, _y == _fsY ? null : _y)));
                _fsX = _x;
                _fsY = _y;
            }
            result.add(g36);
        }
        return result;
    }
}

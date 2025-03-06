package uia.gerber.x2.font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import uia.gerber.x2.GerberX2Statement;
import uia.gerber.x2.model.D01Plot;
import uia.gerber.x2.model.D02Move;
import uia.gerber.x2.model.G36Region;
import uia.gerber.x2.model.LP;

public abstract class ASCII {

    protected abstract int[][] getData();

    private GFont gFont;

    public abstract int getWidth();

    public ASCII font(GFont gFont) {
        this.gFont = gFont;
        return this;
    }

    public void println() {
        int w = getWidth();
        int h = this.gFont.getHeight();

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

    public List<GerberX2Statement> g36(long fsX, long fsY, long fsH) {
        int[][] data = getData();
        if (data.length == 0) {
            return Collections.emptyList();
        }

        int scale = (int) (fsH / this.gFont.getHeight());
        int _h = scale * this.gFont.getHeight();

        fsY = (fsY + _h + (fsH - _h) / 2);
        List<GerberX2Statement> result = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            int[] path = data[i];

            // dark or clear
            result.add(new LP(path[0] == 0x31));

            int yx = path[1];    // special format, refer TextCreator.java line 249

            long _fsX = fsX + (yx % getWidth()) * scale;
            long _fsY = fsY - (yx / getWidth()) * scale;
            G36Region g36 = new G36Region();
            G36Region.Contour contour = g36.create(new D02Move(_fsX, _fsY));
            for (int j = 2; j < path.length; j++) {
                yx = path[j];
                long _x = fsX + (yx % getWidth()) * scale;
                long _y = fsY - (yx / getWidth()) * scale;
                contour.plot(new D01Plot(_x == _fsX ? null : _x, _y == _fsY ? null : _y));
                _fsX = _x;
                _fsY = _y;
            }
            result.add(g36);
        }
        return result;
    }
}

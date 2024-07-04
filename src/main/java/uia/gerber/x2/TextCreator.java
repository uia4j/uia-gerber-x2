package uia.gerber.x2;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TextCreator {

    private final static byte EMPTY = 0x20;

    private final static byte AREA = 0x30;

    private final static byte PATH = 0x31;

    private final static int YELLOW = Color.yellow.getRGB();

    private final static int WHITE = Color.white.getRGB();

    private static Ptr[] cwPtrs = new Ptr[] {
            TextCreator::p0,
            TextCreator::p1,
            TextCreator::p2,
            TextCreator::p3,
            TextCreator::p4,
            TextCreator::p5,
            TextCreator::p6,
            TextCreator::p7
    };

    private Point pV;

    private Point aV;

    private int dir;

    private int width;

    private int height;

    private byte[] data;

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public List<List<Integer>> build(char ch, Font font) throws Exception {
        String one = new String(new char[] { ch });

        Canvas ca = new Canvas();
        FontMetrics m = ca.getFontMetrics(font);
        this.width = m.stringWidth(one);
        this.height = m.getHeight();

        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = image.createGraphics();
        g2d.setFont(font);
        g2d.setColor(Color.white);
        g2d.drawString(one, 0, m.getAscent());
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        AffineTransform transform = g2d.getTransform();
        transform.translate(0, m.getAscent());

        TextLayout layout = new TextLayout(
                one,
                font,
                g2d.getFontRenderContext());
        // Shape
        GeneralPath shape = (GeneralPath) layout.getOutline(transform);
        g2d.setColor(Color.yellow);
        g2d.draw(shape);

        this.data = new byte[this.width * this.height];

        // fill
        for (int y = 0; y < this.height; y++) {
            boolean inA = false;
            for (int x = 0; x < this.width; x++) {
                if (image.getRGB(x, y) == WHITE) {
                    this.data[y * this.width + x] = (x == this.width - 1) || x == 0 ? PATH : AREA;
                    inA = true;
                }
                else if (image.getRGB(x, y) == YELLOW) {
                    this.data[y * this.width + x] = PATH;
                    inA = false;
                }
                else {
                    this.data[y * this.width + x] = inA ? AREA : EMPTY;
                }
            }
        }

        List<List<Integer>> paths = new ArrayList<>();
        byte deep = 0x31;
        while (true) {
            List<Integer> path = walk(++deep);
            if (path == null) {
                break;
            }
            if (!path.isEmpty()) {
                paths.add(path);
            }
        }

        return paths;
    }

    public void print() {
        for (int y = 0; y < this.height; y++) {
            System.out.printf("%03d ", y);
            for (int x = 0; x < this.width; x++) {
                byte b = this.data[y * this.width + x];
                System.out.print(b == EMPTY ? " " : b == AREA ? "." : (char) b);
            }
            System.out.println();
        }
    }

    public void print(List<List<Integer>> paths) {
        System.out.println("width = " + this.width);
        System.out.println("height = " + this.height);

        int i = 0;
        System.out.println("private static final int[][] paths;");
        System.out.println("static {");
        System.out.println("    paths = new int[" + paths.size() + "][];");
        for (List<Integer> path : paths) {
            System.out.print("    paths[" + i + "] = new int[] { 0x31,");
            int p = path.get(0);
            int x = p % this.width;
            int y = p / this.width;
            int xs = -2;
            int ys = -2;
            boolean reduce = false;
            System.out.print(p + ",");
            for (int j = 1; j < path.size(); j++) {
                p = path.get(j);
                int _x = p % this.width;
                int _y = p / this.width;
                int _xs = _x - x;
                int _ys = _y - y;
                if (_xs == 0) {
                    reduce = true;
                    if (ys == 0) {
                        System.out.print((y * this.width + x) + ",");
                    }
                }
                else if (_ys == 0) {
                    reduce = true;
                    if (xs == 0) {
                        System.out.print((y * this.width + x) + ",");
                    }
                }
                else {
                    if (reduce) {
                        System.out.print((y * this.width + x) + ",");
                    }
                    reduce = false;
                    System.out.print(p + ",");
                }
                x = _x;
                y = _y;
                xs = _xs;
                ys = _ys;
            }
            System.out.println(path.get(0) + " };");
            i++;
        }
        System.out.println("}");
    }

    private List<Integer> walk(byte code) {
        this.pV = null;
        this.aV = null;

        int start = -1;
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] == PATH) {
                start = i;
                break;
            }
        }
        if (start < 0) {
            return null;
        }

        boolean left = start % this.width == 0;
        boolean right = (start + 1) % this.width == 0;
        boolean top = (start / this.width) == 0;
        boolean bottom = (start / this.width) == this.height - 1;

        int pa = check(-1, 1, this.data[start - this.width - 1], 0, top || left)
                + check(0, 1, this.data[start - this.width], 1, top)
                + check(1, 1, this.data[start - this.width + 1], 2, top || right)
                + check(1, 0, this.data[start + 1], 3, right)
                + check(1, -1, this.data[start + this.width + 1], 4, bottom || right)
                + check(0, -1, this.data[start + this.width], 5, bottom)
                + check(-1, -1, this.data[start + this.width - 1], 6, bottom || left)
                + check(-1, 0, this.data[start - 1], 7, left);
        if (pa != 2) {
            this.data[start] = EMPTY;
            String msg = String.format("x%s,y%s start failed, p=%s,a=%s", start % this.width, start / this.width, this.pV, this.aV);
            System.out.println(msg);
            return walk(code);
        }

        // boolean cw = (this.pV.x * this.aV.y - this.pV.y * this.aV.x) < 0;
        // Ptr[] ptrs = cw ? cwPtrs : ccwPtrs;
        Ptr[] ptrs = cwPtrs;

        List<Integer> path = new ArrayList<>();
        path.add(start);

        int curr = start - this.pV.y * this.width + this.pV.x;
        this.data[start] = code;
        int c = 0;
        do {
            boolean found = false;
            int dir = (this.dir + 5) % 8;
            for (int i = 0; i < 7; i++) {
                Pt pt = ptrs[(dir + i) % 8].get(curr, this.width, this.height);
                if (pt != null && (pt.index == start || this.data[pt.index] == PATH)) {
                    // System.out.printf("%4s(%4s,%4s) next %s,%4s\n", curr, curr % this.width, curr / this.width, this.dir, pt.index);

                    path.add(curr);
                    this.data[curr] = code;
                    curr = pt.index;
                    found = true;
                    this.dir = pt.dir;
                    break;
                }
            }

            if (!found) {
                String msg = String.format("%s> %5d(x%s,y%s) path missing", (char) code, curr, curr % this.width, curr / this.width);
                System.out.println(msg);
                this.data[curr] = 0x21;
                this.dir = (this.dir + 4) % 8;
                curr = path.remove(path.size() - 1);
            }

            c++;
        }
        while (curr != start && c++ < this.data.length);
        return path;
    }

    private int check(int x, int y, byte value, int dir, boolean ignore) {
        if (ignore) {
            return 0;
        }
        if (this.pV == null && value == PATH) {
            this.dir = dir;
            this.pV = new Point(x, y);
            return 1;
        }

        if (this.aV == null && value == AREA) {
            this.aV = new Point(x, y);
            return 1;
        }

        return 0;
    }

    private static Pt p0(int center, int w, int h) {
        return center / w == 0 || center % w == 0 ? null : new Pt(center - w - 1, 0);
    }

    private static Pt p1(int center, int w, int h) {
        return center / w == 0 ? null : new Pt(center - w, 1);
    }

    private static Pt p2(int center, int w, int h) {
        return center / w == 0 || (center + 1) % w == 0 ? null : new Pt(center - w + 1, 2);
    }

    private static Pt p3(int center, int w, int h) {
        return (center + 1) % w == 0 ? null : new Pt(center + 1, 3);
    }

    private static Pt p4(int center, int w, int h) {
        return center / w == h - 1 || (center + 1) % w == 0 ? null : new Pt(center + w + 1, 4);
    }

    private static Pt p5(int center, int w, int h) {
        return center / w == h - 1 ? null : new Pt(center + w, 5);
    }

    private static Pt p6(int center, int w, int h) {
        return center / w == h - 1 || center % w == 0 ? null : new Pt(center + w - 1, 6);
    }

    private static Pt p7(int center, int w, int h) {
        return center % w == 0 ? null : new Pt(center - 1, 7);
    }

    public interface Ptr {

        public Pt get(int center, int w, int h);
    }

    public static class Pt {

        public final int index;

        public final int dir;

        public Pt(int index, int dir) {
            this.index = index;
            this.dir = dir;
        }
    }
}

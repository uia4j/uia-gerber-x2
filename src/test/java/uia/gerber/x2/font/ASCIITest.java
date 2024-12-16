package uia.gerber.x2.font;

import java.util.List;

import org.junit.Test;

import uia.gerber.x2.GerberX2Statement;

public class ASCIITest {

    @Test
    public void test() {
        int x = 0;
        for (ASCII ascii : Arial.text("x")) {
            List<GerberX2Statement> g36s = ascii.g36(x, 1000000, 100);
            x += ascii.getWidth() * 100;
            g36s.forEach(g36 -> {
                try {
                    g36.write(System.out);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    @Test
    public void testArial() {
        int x = 0;
        for (ASCII ascii : Arial.text("ABCDEFGHIJKLMNOPQRSTUVWXYZ")) {
            List<GerberX2Statement> g36s = ascii.g36(x, 1000000, 100);
            x += ascii.getWidth() * 100;
            g36s.forEach(g36 -> {
                try {
                    g36.write(System.out);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }

        x = 0;
        for (ASCII ascii : Arial.text("abcdefghijklmnopqrstuvwxyz0123456789+-*/.=")) {
            List<GerberX2Statement> g36s = ascii.g36(x, 1050000, 100);
            x += ascii.getWidth() * 100;
            g36s.forEach(g36 -> {
                try {
                    g36.write(System.out);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }

        x = 0;
        for (ASCII ascii : Arial.text("`~!@#$%^&*()_{}[]<>|\\/'\"")) {
            List<GerberX2Statement> g36s = ascii.g36(x, 1100000, 100);
            x += ascii.getWidth() * 100;
            g36s.forEach(g36 -> {
                try {
                    g36.write(System.out);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
        }
    }
}

package uia.gerber.x2;

import java.io.IOException;

import org.junit.Test;

import uia.gerber.x2.model.LNLayer;

@SuppressWarnings("deprecation")
public class GerberX2FileReaderTest implements GerberX2FileReaderListener {

    @Test
    public void test0() throws IOException {
        GerberX2FileReader r = new GerberX2FileReader(this);
        r.run("samples/gerber0.gbr");
    }

    @Test
    public void test1() throws IOException {
        GerberX2FileReader r = new GerberX2FileReader(this);
        r.run("samples/gerber1.gbr");
    }

    @Test
    public void test2() throws IOException {
        GerberX2FileReader r = new GerberX2FileReader(this);
        r.run("samples/gerber2.gbr");
    }

    @Test
    public void test3() throws IOException {
        GerberX2FileReader r = new GerberX2FileReader(this);
        r.run("samples/gerber3.gbr");
    }

    @Test
    public void test4() throws IOException {
        GerberX2FileReader r = new GerberX2FileReader(this);
        r.run("samples/gerber4.gbr");
    }

    @Test
    public void test5() throws IOException {
        GerberX2FileReader r = new GerberX2FileReader(this);
        r.run("samples/gerber5.gbr");
    }

    @Test
    public void test6() throws IOException {
        GerberX2FileReader r = new GerberX2FileReader(this);
        r.run("samples/gerber6.gbr");
    }

    @Test
    public void test7() throws IOException {
        GerberX2FileReader r = new GerberX2FileReader(this);
        r.run("samples/gerber7.gbr");
    }

    @Test
    public void test8() throws IOException {
        GerberX2FileReader r = new GerberX2FileReader(this);
        r.run("samples/gerber8.gbr");
    }

    @Test
    public void test9() throws IOException {
        GerberX2FileReader r = new GerberX2FileReader(this);
        r.run("samples/gerber9.gbr");
    }

    @Test
    public void testD() throws IOException {
        GerberX2FileReader r = new GerberX2FileReader(this);
        r.run("samples/gerberD.gbr");
    }

    @Override
    public void unknown(int lineNo, String cmd) {
        System.out.println(lineNo + " unk, " + cmd);
    }

    @Override
    public void error(int lineNo, String cmd, Exception ex) {
        System.out.println(lineNo + " err, " + cmd);
    }

    @Override
    public void enterLayer(int lineNo, LNLayer layer) {
        System.out.println("Layer=" + layer.getName());
    }

    @Override
    public void enter(int lineNo, GerberX2Statement stmt) {
        try {
            stmt.write(System.out);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    private void out(GerberX2Statement stmt) {
        try {
            stmt.write(System.out);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

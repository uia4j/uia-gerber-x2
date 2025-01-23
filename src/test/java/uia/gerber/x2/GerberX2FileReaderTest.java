package uia.gerber.x2;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;

import org.junit.Test;

import uia.gerber.x2.model.G36Region;
import uia.gerber.x2.model.G36Region.Contour;

public class GerberX2FileReaderTest {

    @Test
    public void testVisitor() throws IOException {
        GerberX2FileReader reader = new GerberX2FileReader(new GerberX2Visitor() {

            @Override
            public void stat(GerberX2Statement stmt) {
                System.out.println(stmt.getCmd() + " attrs=" + stmt.getAttributes());
            }

            @Override
            public void cmd(String stmt) {
            }

            @Override
            public void extCmd(String stmt) {
            }

            @Override
            public void error(String stmt) {
                System.out.println("error - " + stmt);
            }
        });
        reader.run(Paths.get("D:/workspace/htks/air/01.req/PLP/v1/gbrs/FL1.gbr"));
    }

    @Test
    public void test() throws IOException {
        GerberX2FileReader reader = new GerberX2FileReader(new GerberX2Visitor.Console());
        reader.run(Paths.get("D:/workspace/htks/air/01.req/PLP/v1/gbrs/FL1.gbr"));
    }

    @Test
    public void test1() throws IOException {
        GerberX2FileReader reader = new GerberX2FileReader(new GerberX2Visitor.Console());
        reader.run(Paths.get("samples/gerber1.gbr"));
    }

    @Test
    public void test2() throws IOException {
        GerberX2FileReader reader = new GerberX2FileReader(new GerberX2Visitor.Console());
        reader.run(Paths.get("samples/gerber2.gbr"));
    }

    @Test
    public void test3() throws IOException {
        GerberX2FileReader reader = new GerberX2FileReader(new GerberX2Visitor.Console());
        reader.run(Paths.get("samples/gerber3.gbr"));
    }

    @Test
    public void test4() throws IOException {
        GerberX2FileReader reader = new GerberX2FileReader(new GerberX2Visitor.Console());
        reader.run(Paths.get("samples/gerber4.gbr"));
    }

    @Test
    public void test5() throws IOException {
        GerberX2FileReader reader = new GerberX2FileReader(new GerberX2Visitor.Console());
        reader.run(Paths.get("samples/gerber5.gbr"));
    }

    @Test
    public void test6() throws IOException {
        GerberX2FileReader reader = new GerberX2FileReader(new GerberX2Visitor() {

            @Override
            public void stat(GerberX2Statement stmt) {
                try {
                    if (stmt instanceof G36Region) {
                        G36Region g36 = (G36Region) stmt;
                        Contour c = g36.contours.get(1);
                        long x = c.d1.getX();
                        long y = c.d1.getY();
                        long i = c.d1.getI();
                        long j = c.d1.getJ();
                        System.out.printf("X%06dY%06d\n",
                                new BigDecimal((x - i) / 100.0).setScale(0, BigDecimal.ROUND_HALF_UP).intValue(),
                                new BigDecimal((y - j) / 100.0).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());

                    }
                }
                catch (Exception ex) {
                    System.out.println(stmt.getCmd() + " failed");
                    ex.printStackTrace();
                }
            }

            @Override
            public void cmd(String stmt) {
            }

            @Override
            public void extCmd(String stmt) {
            }

            @Override
            public void error(String stmt) {
            }

        });
        reader.run(Paths.get("samples/gerber6.gbr"));
    }

    @Test
    public void testSpecial1() throws IOException {
        GerberX2FileReader reader = new GerberX2FileReader(null);
        reader.run(Paths.get("samples/special/P24617000001-A_UBMExposure.gbr"));
    }

    @Test
    public void testSpecial2() throws IOException {
        GerberX2FileReader reader = new GerberX2FileReader(new GerberX2Visitor.Console());
        reader.run(Paths.get("samples/special/Q5_LD2.gbr"));
    }
}

package uia.gerber.x2;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

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
        reader.run(Paths.get("samples/gerber2.gbr"));
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
    public void testSpecial1() throws IOException {
        GerberX2FileReader reader = new GerberX2FileReader(null);
        reader.run(Paths.get("samples/special/P24617000001-A_UBMExposure.gbr"));
    }

    @Test
    public void testSpecial2() throws IOException {
        GerberX2FileReader reader = new GerberX2FileReader(null);
        reader.run(Paths.get("samples/special/Q5_LD2.gbr"));
    }
}

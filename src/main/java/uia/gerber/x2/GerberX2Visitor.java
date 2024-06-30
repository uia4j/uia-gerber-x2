package uia.gerber.x2;

import java.io.IOException;

/**
 * The statement(command) visitor.
 *
 * @author Kyle K. Lin
 *
 */
public interface GerberX2Visitor {

    public void stat(GerberX2Statement stmt);

    public void cmd(String stmt);

    public void extCmd(String stmt);

    public void error(String stmt);

    public static class Console implements GerberX2Visitor {

        @Override
        public void stat(GerberX2Statement stmt) {
            try {
                stmt.write(System.out);
            }
            catch (IOException ex) {
                System.out.println(stmt.getCmd() + " failed");
                ex.printStackTrace();
            }
        }

        @Override
        public void cmd(String stmt) {
            System.out.println(stmt + "*");
        }

        @Override
        public void extCmd(String stmt) {
            System.out.println("%" + stmt + "*%");
        }

        @Override
        public void error(String stmt) {
            System.out.println("unknown statement:\n  " + stmt);

        }

    }
}

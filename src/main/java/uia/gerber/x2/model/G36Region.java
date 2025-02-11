package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uia.gerber.x2.GerberX2Statement;

/**
 * G36 turns on the outline fill.
 *
 * @author Kyle K. Lin
 *
 */
public class G36Region implements GerberX2Statement {

    private List<ATTR> attributes;

    private final List<Contour> contours;

    public G36Region() {
        this.attributes = Collections.emptyList();
        this.contours = new ArrayList<>();
    }

    public Contour create(D02Move d02) {
        Contour c = new Contour(d02);
        this.contours.add(c);
        return c;
    }

    @Override
    public List<ATTR> getAttributes() {
        return this.attributes;
    }

    @Override
    public void setAttributes(List<ATTR> attributes) {
        this.attributes = new ArrayList<>(attributes);
    }

    @Override
    public String getCmd() {
        return "G36";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        for (ATTR attr : this.attributes) {
            attr.write(out);
        }
        out.write("G36*\n".getBytes());
        for (int i = 0; i < this.contours.size(); i++) {
            this.contours.get(i).write(out);
        }
        out.write("G37*\n".getBytes());
    }

    /**
     * 輪廓
     *
     * @author Kyle K. Lin
     *
     */
    public static class Contour {

        private final D02Move d02;

        private final List<IG36Stmt> stmts;

        public Contour(D02Move d02) {
            this.d02 = d02;
            this.stmts = new ArrayList<>();
        }

        /**
         *
         * @param stmt one of D01, G01, G02, G03.
         */
        public Contour plot(IG36Stmt stmt) {
            this.stmts.add(stmt);
            return this;
        }

        public void write(OutputStream out) throws IOException {
            this.d02.write(out);
            for (IG36Stmt stmt : this.stmts) {
                stmt.write(out);
            }
        }
    }
}

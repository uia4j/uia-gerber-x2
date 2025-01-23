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

    public final D02Move start;

    public final List<Contour> contours;

    public G36Region(D02Move start) {
        this.attributes = Collections.emptyList();
        this.start = start;
        this.contours = new ArrayList<>();
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
        this.start.write(out);
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
    public static class Contour implements GerberX2Statement {

        /**
         * 直線
         */
        public static final String G01 = "G01";

        /**
         * 順時針
         */
        public static final String G02 = "G02";

        /**
         * 逆時針
         */
        public static final String G03 = "G03";

        public final String g;

        public final D01Plot d1;

        public final D03Flash d3;

        public Contour(String g) {
            this.g = g;
            this.d1 = null;
            this.d3 = null;
        }

        public Contour(D01Plot d1) {
            this.g = null;
            this.d1 = d1;
            this.d3 = null;
        }

        public Contour(D03Flash d3) {
            this.g = null;
            this.d1 = null;
            this.d3 = d3;
        }

        @Override
        public String getCmd() {
            if (this.g == null) {
                return this.d1 != null ? this.d1.getCmd() : this.d3.getCmd();
            }
            else if (this.d1 != null) {
                return this.g + this.d1.getCmd();
            }
            else if (this.d3 != null) {
                return this.g + this.d3.getCmd();
            }
            else {
                return this.g;
            }
        }

        @Override
        public void write(OutputStream out) throws IOException {
            if (this.g != null) {
                out.write((this.g + "*\n").getBytes());
            }
            else if (this.d1 != null) {
                this.d1.write(out);
            }
            else {
                this.d3.write(out);
            }
        }
    }
}

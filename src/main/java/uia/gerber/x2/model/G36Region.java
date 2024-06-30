package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uia.gerber.x2.GerberX2Statement;

/**
 * G36
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

    public static class Contour implements GerberX2Statement {

        public final String g;

        public final D01Plot d;

        public Contour(String g) {
            this.g = g;
            this.d = null;
        }

        public Contour(D01Plot d) {
            this.g = null;
            this.d = d;
        }

        @Override
        public String getCmd() {
            if (this.g == null) {
                return this.d.getCmd();
            }
            else {
                return this.d != null ? this.g + this.d.getCmd() : this.g;
            }
        }

        @Override
        public void write(OutputStream out) throws IOException {
            if (this.g != null) {
                out.write((this.g + "*\n").getBytes());
            }
            else {
                this.d.write(out);
            }
        }
    }
}

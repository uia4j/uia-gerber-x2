package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * AD(Aperture Definition) creates an aperature.
 *
 * @author Kyle K. Lin
 *
 */
public class AD implements IAD {

    private List<ATTR> attributes;

    private int no;

    private String dCode;

    private String template;

    private List<BigDecimal> xs;

    public AD() {
        this.attributes = new ArrayList<>();
        this.xs = new ArrayList<>();
    }

    public AD(int no, String template) {
        if (no < 10 || no > 999) {
            throw new IllegalArgumentException("number out of range [10..999]");
        }
        this.no = no;
        this.dCode = String.format("D%03d", no);
        this.template = template;
        this.attributes = new ArrayList<>();
        this.xs = new ArrayList<>();
    }

    @Override
    public String getCmd() {
        return "AD";
    }

    @Override
    public int getNo() {
        return this.no;
    }

    @Override
    public String getDnn() {
        return this.dCode;
    }

    @Override
    public List<ATTR> getAttributes() {
        return this.attributes;
    }

    @Override
    public void setAttributes(List<ATTR> attributes) {
        this.attributes = new ArrayList<>(attributes);
    }

    public void setDnn(String dnn) {
        this.dCode = dnn;
    }

    public String getTemplate() {
        return this.template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<BigDecimal> getXs() {
        return this.xs;
    }

    public void setXs(List<BigDecimal> xs) {
        this.xs = xs;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        for (ATTR attr : this.attributes) {
            attr.write(out);
        }
        out.write(String.format("%%AD%S%S,%S", this.dCode, this.template, this.xs.get(0)).getBytes());
        for (int i = 1; i < this.xs.size(); i++) {
            out.write(String.format("X%S", this.xs.get(i)).getBytes());
        }
        out.write("*%\n".getBytes());
    }

    @Override
    public String toString() {
        return String.format("AD%s", this.dCode);
    }
}

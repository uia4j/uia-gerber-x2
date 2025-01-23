package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import uia.gerber.x2.GerberX2Statement;

/**
 * The attribute for TF, TA, TO, TD.<br>
 * <ul>
 *  <li>TF：file attribute.</li>
 *  <li>TA：apertue attribute.</li>
 *  <li>TO：object attribute.</li>
 *  <li>TD：delete attribute.</li>
 * <ul>
 *
 * @author Kyle K. Lin
 *
 */
public class ATTR implements GerberX2Statement {

    private String cmd;

    private String name;

    private final List<String> fields;

    /**
     * The constructor.
     *
     * @param cmd The type of attribute. One of 'TF', 'TA', 'TO', 'TD'.
     */
    public ATTR(String cmd) {
        this.cmd = cmd;
        this.fields = new ArrayList<>();
    }

    @Override
    public String getCmd() {
        return this.cmd;
    }

    /**
     * Returns the attribute name.
     *
     * @return The attribute name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the attribute name.
     *
     * @param name the attribute name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the values of this attribute.
     *
     * @return The values.
     */
    public List<String> getFields() {
        return this.fields;
    }

    @Override
    public String toString() {
        return String.format("%s=%s", this.name, this.fields);
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("%%%S%s", this.cmd, this.name).getBytes());
        for (String f : this.fields) {
            out.write(String.format(",%s", f).getBytes());
        }
        out.write("*%\n".getBytes());
    }
}
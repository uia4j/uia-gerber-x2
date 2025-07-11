package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import uia.gerber.x2.GerberX2Statement;

/**
 * DNN
 *
 * @author Kyle K. Lin
 *
 */
public class Dnn implements GerberX2Statement {

    private final int no;

    private final String name;

    public Dnn(int no) {
        this(no, true);
    }

    public Dnn(int no, boolean write) {
        if (write && (no < 10 || no > 999)) {
            throw new IllegalArgumentException("d" + no + " out of range [10..999]");
        }
        this.no = no;
        this.name = String.format("D%03d", no);
    }

    public int getNo() {
        return this.no;
    }

    @Override
    public String getCmd() {
        return this.name;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("%S*\n", this.name).getBytes());
    }

    @Override
    public String toString() {
        return this.name;
    }
}

package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

import org.antlr.v4.runtime.Token;

import uia.gerber.x2.GerberX2Statement;

/**
 * DNN
 *
 * @author Kyle K. Lin
 *
 */
public class Dnn implements GerberX2Statement {

    private final String name;

    public Dnn(int no) {
        if (no < 10 || no > 999) {
            throw new IllegalArgumentException("number out of range [10..999]");
        }
        this.name = "D" + no;
    }

    public Dnn(Token t) {
        this.name = t.getText();
    }

    @Override
    public String getCmd() {
        return this.name;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("%S*\n", this.name).getBytes());
    }
}

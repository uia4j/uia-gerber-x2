package uia.gerber.x2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import uia.gerber.x2.builder.CommonGraphics;
import uia.gerber.x2.model.FS;
import uia.gerber.x2.model.M02;

public class GerberX2Rescale implements GerberX2FileReaderListener {

    private final GerberX2FileWriter writer;

    private final int intDigi;

    private final int decDigi;

    private final CommonGraphics cg;

    private Valuer valuer;

    public GerberX2Rescale(String newPathname, int intDigi, int decDigi) throws IOException {
        this(new FileOutputStream(new File(newPathname), false), intDigi, decDigi);
    }

    public GerberX2Rescale(OutputStream newOut, int intDigi, int decDigi) throws IOException {
        this.writer = new GerberX2FileWriter(newOut);
        this.intDigi = intDigi;
        this.decDigi = decDigi;
        this.writer.open();
        this.cg = this.writer.getGraphics();
    }

    public GerberX2FileWriter getWriter() {
        return this.writer;
    }

    @Override
    public void unknown(int lineNo, String cmd) {
        System.out.printf("[U]%6d - %s\n", lineNo, cmd);
    }

    @Override
    public void error(int lineNo, String cmd, Exception ex) {
        System.out.printf("[E]%6d - %s, %s\n", lineNo, cmd, ex.getMessage());
    }

    @Override
    public void enter(int lineNo, GerberX2Statement stmt) {
        try {
            if (stmt instanceof M02) {
                return;
            }

            if (stmt instanceof FS) {
                this.valuer = ((FS) stmt).valuer();
                this.writer.start(this.intDigi, this.decDigi, null);
            }
            else {
                stmt.scale(this.valuer, this.writer.fs());
                this.cg.write(stmt);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eof() {
        try {
            this.writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

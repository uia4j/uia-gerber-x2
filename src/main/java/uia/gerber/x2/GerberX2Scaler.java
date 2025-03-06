package uia.gerber.x2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import uia.gerber.x2.builder.CommonGraphics;
import uia.gerber.x2.model.FS;
import uia.gerber.x2.model.M02;

public final class GerberX2Scaler implements GerberX2FileReaderListener {

    private final GerberX2FileWriter writer;

    private final int intDigi;

    private final int decDigi;

    private final CommonGraphics cg;

    private Valuer valuer;

    public GerberX2Scaler(String filePath, int intDigi, int decDigi) throws IOException {
        this.writer = new GerberX2FileWriter(new FileOutputStream(new File(filePath), false));
        this.intDigi = intDigi;
        this.decDigi = decDigi;
        this.writer.open();
        this.cg = this.writer.getGraphics();
    }

    @Override
    public void unknown(int lineNo, String cmd) {
        System.out.printf("[U]%6d - %s\n", lineNo, cmd);
    }

    @Override
    public void error(int lineNo, String cmd) {
        System.out.printf("[E]%6d - %s\n", lineNo, cmd);
    }

    @Override
    public void enter(int lineNo, GerberX2Statement stmt) {
        try {
            if (stmt instanceof M02) {
                return;
            }

            if (stmt instanceof FS) {
                this.valuer = ((FS) stmt).valuer();
                this.writer.start(this.intDigi, this.decDigi);
            }
            else {
                stmt.scale(this.valuer, this.writer.fs());
                this.cg.write(stmt);
            }
        }
        catch (Exception e) {

        }
    }

    @Override
    public void eof() {
        try {
            this.writer.close();
        }
        catch (IOException e) {

        }
    }
}

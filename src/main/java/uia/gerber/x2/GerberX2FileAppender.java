package uia.gerber.x2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import uia.gerber.x2.builder.CommonGraphics;
import uia.gerber.x2.model.FS;
import uia.gerber.x2.model.IAD;
import uia.gerber.x2.model.LP;
import uia.gerber.x2.model.M02;
import uia.gerber.x2.model.MO;
import uia.gerber.x2.model.MO.UnitType;

public class GerberX2FileAppender implements GerberX2FileReaderListener {

    private final GerberX2FileWriter writer;

    private final CommonGraphics cg;

    private Integer intDigi;

    private Integer decDigi;

    private boolean scale;

    private boolean dark;

    private boolean append;

    private Valuer origFS;

    public GerberX2FileAppender(String newPathname) throws IOException {
        this(new FileOutputStream(new File(newPathname), false));
    }

    public GerberX2FileAppender(OutputStream newOut) throws IOException {
        this.writer = new GerberX2FileWriter(newOut);
        this.intDigi = null;
        this.decDigi = null;
        this.append = false;
        this.scale = false;
        this.writer.open();
        this.cg = this.writer.getGraphics();
    }

    public GerberX2FileAppender(String newPathname, int newIntDigi, int newDecDigi) throws IOException {
        this(new FileOutputStream(new File(newPathname), false), newIntDigi, newDecDigi);
    }

    public GerberX2FileAppender(OutputStream newOut, int newIntDigi, int newDecDigi) throws IOException {
        this.writer = new GerberX2FileWriter(newOut);
        this.intDigi = newIntDigi;
        this.decDigi = newDecDigi;
        this.append = false;
        this.scale = false;
        this.writer.open();
        this.cg = this.writer.getGraphics();
    }

    public GerberX2FileWriter read(String origPathname) throws IOException {
        if (origPathname == null) {
            // for test only
            this.writer.start(3, 6, UnitType.MM);
        }
        else {
            GerberX2FileReader reader = new GerberX2FileReader(this);
            reader.run(origPathname);
        }
        return this.writer;
    }

    public GerberX2FileWriter read(InputStream origPathname) throws IOException {
        if (origPathname == null) {
            // for test only
            this.writer.start(3, 6, UnitType.MM);
        }
        else {
            GerberX2FileReader reader = new GerberX2FileReader(this);
            reader.run(origPathname);
        }
        return this.writer;
    }

    public GerberX2FileWriter append(InputStream origPathname) throws IOException {
        if (origPathname == null) {
            return null;
        }

        this.append = true;
        GerberX2FileReader reader = new GerberX2FileReader(this);
        reader.run(origPathname);
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
                this.writer.setDark(this.dark);
                return;
            }

            if (stmt instanceof FS) {
                if (!this.append) {
                    this.origFS = ((FS) stmt).valuer();
                    if (this.intDigi == null || this.decDigi == null) {
                        this.intDigi = this.origFS.intDigi();
                        this.decDigi = this.origFS.decDigi();
                    }
                    this.writer.start(this.intDigi, this.decDigi, null);
                    this.scale = !this.origFS.same(this.writer.fs());
                }
                else {
                    this.origFS = ((FS) stmt).valuer();
                    this.scale = !this.origFS.same(this.writer.fs());
                }

            }
            else if (stmt instanceof LP) {
                this.dark = ((LP) stmt).isDark();
            }
            else if (stmt instanceof IAD) {
                this.writer.addDnn(((IAD) stmt).getNo());
            }
            else if (stmt instanceof MO) {
                this.cg.write(stmt);
            }
            else {
                if (this.scale) {
                    stmt.scale(this.origFS, this.writer.fs());
                }
                this.cg.write(stmt);
            }
        }
        catch (Exception e) {

        }
    }

    @Override
    public void eof() {
    }
}

package uia.gerber.x2;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uia.gerber.x2.builder.CommonGraphics;
import uia.gerber.x2.model.ATTR;
import uia.gerber.x2.model.FS;
import uia.gerber.x2.model.G01;
import uia.gerber.x2.model.G04Comment;
import uia.gerber.x2.model.G75;
import uia.gerber.x2.model.MO;

/**
 * The Gerber X2 layout file writer.
 *
 * @author Kyle K. Lin
 *
 */
public class GerberX2FileWriter {

    private final OutputStream out;

    private String description;

    private MO.UnitType unit;

    private Valuer fsValuer;

    private int step;

    private List<Integer> ad;

    private CommonGraphics graphics;

    private List<ATTR> attrs;

    private int rowCount;

    private GerberX2FileWriterDebugger debugger;

    private boolean dark;

    /**
     * The constructor.
     *
     * @param out The output stream.
     */
    public GerberX2FileWriter(OutputStream out) {
        this.out = new OutputStreamExt(out);
        this.unit = MO.UnitType.MM;
        this.fsValuer = new Valuer(3, 6);
        this.ad = new ArrayList<>();
        this.attrs = new ArrayList<ATTR>();
    }

    /**
     * Return the debugger.
     *
     * @return The debugger.
     */
    public GerberX2FileWriterDebugger getDebugger() {
        return this.debugger;
    }

    /**
     * Sets the debugger.
     * @param debugger A debugger.
     */
    public void setDebugger(GerberX2FileWriterDebugger debugger) {
        this.debugger = debugger;
    }

    /**
     * Returns the output stream.
     *
     * @return The output stream.
     */
    public OutputStream getOutputStream() {
        return this.out;
    }

    /**
     * Adds a file attribute.
     * @param name The attribute name.
     * @param fields fields.
     *
     * @return This writer.
     */
    public GerberX2FileWriter attr(String name, String... fields) {
        ATTR attr = new ATTR("TF");
        attr.setName(name);
        for (String f : fields) {
            attr.getFields().add(f);
        }
        this.attrs.add(attr);
        return this;
    }

    /**
     * Sets the description.
     *
     * @param description The description.
     * @return This writer.
     */
    public GerberX2FileWriter description(String description) {
        if (this.step == 0) {
            this.description = description;
        }
        return this;
    }

    /**
     * Sets the unit.
     *
     * @param unit The unit.
     * @return This writer.
     */
    public GerberX2FileWriter unit(MO.UnitType unit) {
        if (this.step == 0) {
            this.unit = unit;
        }
        return this;
    }

    /**
     * Sets the format specification.
     *
     * @param intDigi The digi of integer.
     * @param decDigi The digi of decimal.
     * @return This writer.
     */
    public GerberX2FileWriter fs(int intDigi, int decDigi) {
        if (this.step == 0) {
            this.fsValuer = new Valuer(intDigi, decDigi);
        }
        return this;
    }

    /**
     * Checks if the graphics is dark mode.
     *
     * @return True if graphics is dark.
     */
    public boolean isDark() {
        return this.dark;
    }

    /**
     * Sets if the graphics is dark mode.
     *
     * @param dark True if graphics is dark.
     */
    public void setDark(boolean dark) {
        this.dark = dark;
    }

    /**
     * Returns the format specification.
     *
     * @return The format specification.
     */
    public Valuer fs() {
        return this.fsValuer;
    }

    /**
     * Converts the value to FS format.
     *
     * @param v The value.
     * @return Value meets FS format.
     */
    public Long xy(long v) {
        return this.fsValuer.out(v);
    }

    /**
     * Converts the value to FS format.
     *
     * @param v The value.
     * @return Value meets FS format.
     */
    public Long xy(double v) {
        return this.fsValuer.out(v);
    }

    /**
     * Converts the value to FS format.
     *
     * @param v The value.
     * @return Value meets FS format.
     */
    public Long xy(BigDecimal v) {
        return this.fsValuer.out(v);
    }

    /**
     * Checks if d code is exist or not.
     *
     * @param dCode The d code.
     * @return True if the d code already exists.
     */
    public boolean contains(int dCode) {
        return this.ad.contains(dCode);
    }

    /**
     * Adds a new d code.
     * @param dCode The d code.
     *
     * @return True if the new d code is added. False if the d code already exists.
     */
    public boolean addDnn(int dCode) {
        if (this.ad.contains(dCode)) {
            return false;
        }

        this.ad.add(dCode);
        return true;
    }

    /**
     * Returns row count.
     * @return The row count.
     */
    public int getRowCount() {
        return this.rowCount;
    }

    /**
     * Starts the writer.
     *
     * @return 0: success, 1: started already, 2: closed.
     * @throws IOException Failed to write records into output stream.
     */
    public int start() throws IOException {
        if (this.step != 0) {
            return this.step;
        }

        this.rowCount = 0;

        new G04Comment(this.description).write(this.out);
        new FS(this.fsValuer).write(this.out);
        new MO(this.unit).write(this.out);

        ATTR attr0 = new ATTR("TF");
        attr0.setName(".CreationDate");
        attr0.getFields().add(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").format(new Date()));
        attr0.write(this.out);
        for (ATTR attr : this.attrs) {
            attr.write(this.out);
        }
        new G01().write(this.out);
        new G75().write(this.out);

        this.step = 1;
        this.graphics = new CommonGraphics(this);
        return 0;
    }

    /**
     * Returns the graphics object.
     *
     * @return The graphics object.
     */
    public CommonGraphics getGraphics() {
        return this.graphics;
    }

    /**
     * Stops the writer.
     *
     * @return 0: passed, 1: not started, 2: closed.
     * @throws IOException
     */
    public int stop() throws IOException {
        if (this.step == 0) {
            return 1;
        }
        if (this.step == 2) {
            return 2;
        }
        this.graphics.close();
        this.graphics = null;
        this.out.write("M02*\n".getBytes());
        this.step = 2;
        return 0;
    }

    private void debug() {
        this.rowCount++;
        if (this.debugger != null && this.debugger.breakLine == this.rowCount) {
            this.debugger.reached();
        }
    }

    /**
     * The output stream adapter.
     *
     * @author Kyle K. Lin
     *
     */
    class OutputStreamExt extends OutputStream {

        private final OutputStream out;

        OutputStreamExt(OutputStream out) {
            this.out = out;
        }

        @Override
        public void write(int b) throws IOException {
            this.out.write(b);
            if (b == '\n') {
                debug();
            }
        }

        @Override
        public void write(byte b[]) throws IOException {
            for (byte _b : b) {
                if (_b == '\n') {
                    debug();
                }
            }
            this.out.write(b, 0, b.length);
        }

        @Override
        public void write(byte b[], int off, int len) throws IOException {
            for (int x = off; x < off + len; x++) {
                if (b[x] == '\n') {
                    debug();
                }
            }
            this.out.write(b, off, len);
        }

        @Override
        public void flush() throws IOException {
            this.out.flush();
        }

        @Override
        public void close() throws IOException {
            this.out.close();
        }

    }
}

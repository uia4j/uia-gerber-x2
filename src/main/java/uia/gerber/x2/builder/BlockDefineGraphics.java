package uia.gerber.x2.builder;

import java.io.IOException;

import uia.gerber.x2.GerberX2FileWriter;

/**
 * AB Block Aperture.
 *
 * @author Kyle K. Lin
 *
 */
public class BlockDefineGraphics extends CommonGraphics {

    private final boolean initDark;

    private final int dCode;

    /**
     * The constructor.
     *
     * @param dCode The d code
     * @param writer The writer.
     * @throws IOException Failed to write to output stream.
     */
    BlockDefineGraphics(int dCode, GerberX2FileWriter writer) throws IOException {
        super(writer);
        this.initDark = writer.isDark();
        this.dCode = dCode;
        this.out.write(String.format("%%ABD%03d*%%\n", dCode).getBytes());
    }

    /**
     * Returns the d code.
     *
     * @return The d code.
     */
    public int getDCode() {
        return this.dCode;
    }

    @Override
    public CommonGraphics layer(String name) throws IOException {
        throw new IOException("LN not supported in AB");
    }

    @Override
    public StepRepeatGraphics createStepRepeat(int fsX, int fsY, long fsI, long fsJ) throws IOException {
        throw new IOException("AB doesn't support SR(StepRepeat).");
    }

    @Override
    public void close() throws IOException {
        if (this.out != null) {
            GerberX2FileWriter writer = this.writer;
            super.close();
            this.out.write("%AB*%\n".getBytes());
            this.out = null;
            writer.setDark(this.initDark);
        }
    }
}

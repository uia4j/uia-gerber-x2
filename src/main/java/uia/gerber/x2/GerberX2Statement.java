package uia.gerber.x2;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import uia.gerber.x2.model.ATTR;

/**
 * The Gerber X2 layout statement(command).
 *
 * @author Kyle K. Lin
 *
 */
public interface GerberX2Statement {

    /**
     * Returns the command.
     * @return
     */
    public String getCmd();

    /**
     * Returns the attributes. Default is empty.
     *
     * @return The attributes.
     */
    public default List<ATTR> getAttributes() {
        return Collections.emptyList();
    }

    /**
     * Sets the attributes.
     *
     * @param attrs The attributes.
     */
    public default void setAttributes(List<ATTR> attrs) {
    }

    public default void scale(Valuer from, Valuer to) {
    }

    /**
     * Writes the statement to the output stream.
     *
     * @param out The output stream.
     * @throws IOException Failed to write data to the output stream.
     */
    public void write(OutputStream out) throws IOException;

    public static class UNK implements GerberX2Statement {

        private final String line;

        private final boolean ext;

        public UNK(String line, boolean ext) {
            this.line = line;
            this.ext = ext;
        }

        @Override
        public String getCmd() {
            return this.line;
        }

        @Override
        public void write(OutputStream out) throws IOException {
            if (ext) {
                out.write(("%" + this.line + "*%\n").getBytes());
            }
            else {
                out.write((this.line + "*\n").getBytes());
            }
        }

    }

    public static class LINE implements GerberX2Statement {

        private final String line;

        public LINE(String line) {
            this.line = line;
        }

        @Override
        public String getCmd() {
            return this.line;
        }

        @Override
        public void write(OutputStream out) throws IOException {
            out.write((this.line + "\n").getBytes());
        }

    }
}

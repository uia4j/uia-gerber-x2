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

    /**
     * Writes the statement to the output stream.
     *
     * @param out The output stream.
     * @throws IOException Failed to write data to the output stream.
     */
    public void write(OutputStream out) throws IOException;
}

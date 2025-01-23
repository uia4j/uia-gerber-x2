package uia.gerber.x2.builder;

import java.io.IOException;

/**
 * Gerber X2 graphics.
 *
 * @author Kyle K. Lin
 *
 */
public interface GerberX2Graphics {

    public int lastState();

    /**
     * Closes the graphics.
     *
     * @throws IOException Failed to write to the output stream.
     */
    public void close() throws IOException;

}

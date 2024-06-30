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

    public void close() throws IOException;

}

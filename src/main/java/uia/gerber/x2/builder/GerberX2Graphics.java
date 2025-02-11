package uia.gerber.x2.builder;

import java.io.IOException;

/**
 * Gerber X2 graphics.
 *
 * @author Kyle K. Lin
 *
 */
public interface GerberX2Graphics {

    /**
     * 直線
     */
    public static final String G01 = "G01";

    /**
     * 順時針
     */
    public static final String G02 = "G02";

    /**
     * 逆時針
     */
    public static final String G03 = "G03";

    public int lastState();

    /**
     * Closes the graphics.
     *
     * @throws IOException Failed to write to the output stream.
     */
    public void close() throws IOException;

}

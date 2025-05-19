package uia.gerber.x2;

import uia.gerber.x2.model.AB;
import uia.gerber.x2.model.ABBlock;
import uia.gerber.x2.model.AM;
import uia.gerber.x2.model.G36Region;
import uia.gerber.x2.model.IAD;
import uia.gerber.x2.model.LNLayer;

@SuppressWarnings("deprecation")
public interface GerberX2FileReaderListener {

    /**
     * Gets a unknown command.
     *
     * @param lineNo The line no.
     * @param cmd The command.
     */
    public default void unknown(int lineNo, String cmd) {
    }

    /**
     * failed to handle a command.
     *
     * @param lineNo The line no.
     * @param cmd The command.
     * @param ex The exception, maybe null.
     */
    public default void error(int lineNo, String cmd, Exception ex) {
    }

    /**
     * Enters into a command.
     *
     * @param lineNo The line no.
     * @param stmt The statement.
     */
    public default void enter(int lineNo, GerberX2Statement stmt) {
    }

    /**
     * Before M2.
     *
     * @param lineNo The line no.
     */
    public default void beforeEnd(int lineNo) {
    }

    /**
     * End of file.
     */
    public default void eof() {
    }

    /**
     * An aperture is defined.
     *
     * @param lineNo The line no.
     * @param stmt The statement.
     */
    public default void apertureDefined(int lineNo, IAD stmt) {
    }

    /**
     * Enters into a block(AB).
     *
     * @param lineNo The line no.
     * @param ab The AB statement.
     */
    public default void enterAB(int lineNo, AB ab) {
    }

    /**
     * Exits a block(AB).
     *
     * @param lineNo The line no.
     * @param blcok The total commands in the block
     */
    public default void exitAB(int lineNo, ABBlock blcok) {
    }

    /**
     * Enters into a macro(AM).
     *
     * @param lineNo The line no.
     * @param ab The AM statement.
     */
    public default void enterAM(int lineNo, AM am) {
    }

    /**
     * Enters into a range(G36).
     *
     * @param lineNo The line no.
     */
    public default void enterG36(int lineNo) {
    }

    /**
     * Exits a range(G37).
     *
     * @param lineNo The line no.
     * @param g36Region The total command in the region.
     */
    public default void exitG36(int lineNo, G36Region g36Region) {
    }

    /**
     * Enters into a layer(LN).
     *
     * @param lineNo The line no.
     * @param layer The layer.
     */
    public default void enterLayer(int lineNo, LNLayer layer) {
    }

    /**
     * Exist a layer(LN).
     *
     * @param lineNo The line no.
     * @param layer The layer.
     */
    public default void exitLayer(int lineNo, LNLayer layer) {
    }
}

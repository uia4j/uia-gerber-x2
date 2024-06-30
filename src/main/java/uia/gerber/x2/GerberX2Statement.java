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

    public String getCmd();

    public default List<ATTR> getAttributes() {
        return Collections.emptyList();
    }

    public default void setAttributes(List<ATTR> attrs) {
    }

    public void write(OutputStream out) throws IOException;
}

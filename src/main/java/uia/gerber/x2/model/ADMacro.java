package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * AD: Aperture Definition (Circle).
 *
 * @author Kyle K. Lin
 *
 */
public class ADMacro extends AD {

    private final AM am;

    /**
     * The constructor.
     *
     * @param diameter The diameter.
     * @param holeDiameter The hole diameter.
     */
    public ADMacro(int no, AM am) {
        super(no, am.getName());
        this.am = am;
    }

    public AM getAm() {
        return this.am;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        for (ATTR attr : getAttributes()) {
            attr.write(out);
        }
        out.write(String.format("%%AD%S%s*%%\n", getDnn(), this.getTemplate()).getBytes());
    }

}

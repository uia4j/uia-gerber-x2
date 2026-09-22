package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;

/**
 * G54(Tool Prepare) precedes an aperture D-code. Deprecated in 2012.
 *
 * @author Kyle K. Lin
 *
 */
@Deprecated
public class G54 implements IState {

    private final int no;

    private final String dnn;

    public G54(String dnn) {
        this.no = Integer.parseInt(dnn.substring(1));
        this.dnn = dnn;
    }

    @Override
    public String getCmd() {
        return "G54";
    }

    public int getNo() {
        return this.no;
    }

    public String getDnn() {
        return this.dnn;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(String.format("G54D%03d*\n", this.no).getBytes());
    }

}

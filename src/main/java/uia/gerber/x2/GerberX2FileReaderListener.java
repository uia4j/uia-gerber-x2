package uia.gerber.x2;

import uia.gerber.x2.model.AB;
import uia.gerber.x2.model.ABBlock;
import uia.gerber.x2.model.G36Region;
import uia.gerber.x2.model.IAD;

public interface GerberX2FileReaderListener {

    public void enter(int seqNo, GerberX2Statement stmt);

    public void unknown(int seqNo, String cmd);

    public void error(int seqNo, String cmd);

    public void apertureDefined(IAD stmt);

    public void enterAB(AB ab);

    public void exitAB(ABBlock blcok);

    public void enterG36();

    public void exitG36(G36Region g36Region);

    public void beforeEnd();
}

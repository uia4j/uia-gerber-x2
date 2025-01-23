package uia.gerber.x2;

public abstract class GerberX2FileWriterDebugger {

    final int breakLine;

    public GerberX2FileWriterDebugger(int breakLine) {
        this.breakLine = breakLine;
    }

    public abstract void reached();
}

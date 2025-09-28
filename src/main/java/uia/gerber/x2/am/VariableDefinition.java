package uia.gerber.x2.am;

public class VariableDefinition implements MacroBlock {

    private final String cmd;

    public VariableDefinition(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return this.cmd;
    }
}

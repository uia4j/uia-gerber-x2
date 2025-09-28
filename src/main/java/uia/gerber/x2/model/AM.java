package uia.gerber.x2.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import uia.gerber.x2.GerberX2Statement;
import uia.gerber.x2.am.MacroBlock;
import uia.gerber.x2.am.MacroValues;
import uia.gerber.x2.am.Primitive;
import uia.gerber.x2.am.PrimitiveComment;
import uia.gerber.x2.am.PrimitiveOutline;
import uia.gerber.x2.am.VariableDefinition;

public class AM implements GerberX2Statement {

    private final String name;

    private final MacroValues values;

    private final List<String> body;

    public AM(String name, MacroValues values) {
        this.name = name;
        this.values = values;
        this.body = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<String> getBody() {
        return this.body;
    }

    public List<MacroBlock> parse() throws Exception {
        List<MacroBlock> blocks = new ArrayList<>();
        String cmd = "";
        for (String line : this.body) {
            cmd += line;
            if (line.endsWith("*")) {
                cmd = cmd.substring(0, cmd.length() - 1);
                if (cmd.startsWith("$")) {
                    blocks.add(variableDefinition(cmd));
                }
                else {
                    blocks.add(primitive(cmd));
                }
                cmd = "";
            }
        }
        return blocks;
    }

    private VariableDefinition variableDefinition(String cmd) {
        String[] vs = cmd.split("=");
        this.values.add(vs[0], vs[1]);
        return new VariableDefinition(cmd);
    }

    private Primitive primitive(String cmd) throws Exception {
        if (cmd.startsWith("0 ")) {
            return new PrimitiveComment(cmd);
        }
        else if (cmd.startsWith("1,")) {
            throw new Exception("Not implemntation");
        }
        else if (cmd.startsWith("20,")) {
            throw new Exception("Not implemntation");
        }
        else if (cmd.startsWith("21,")) {
            throw new Exception("Not implemntation");
        }
        else if (cmd.startsWith("4,")) {
            return new PrimitiveOutline(cmd, this.values);
        }
        else if (cmd.startsWith("5,")) {
            throw new Exception("Not implemntation");
        }
        else if (cmd.startsWith("7,")) {
            throw new Exception("Not implemntation");
        }
        else {
            throw new Exception("Unknown");
        }
    }

    @Override
    public String getCmd() {
        return "AM";
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(("%AM" + this.name + "*\n").getBytes());
        for (String line : this.body) {
            out.write((line + "\n").getBytes());
        }
        out.write(("%\n").getBytes());
    }

}

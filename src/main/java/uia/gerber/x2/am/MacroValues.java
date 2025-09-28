package uia.gerber.x2.am;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class MacroValues {

    public Map<String, String> varExpList;

    public MacroValues() {
        this.varExpList = new TreeMap<>();
    }

    public void add(String variable, String exp) {
        this.varExpList.put(variable, exp);
    }

    public BigDecimal num(String varOrValue) throws Exception {
        if (varOrValue.startsWith("$")) {
            throw new Exception("not supoort $");
        }

        return new BigDecimal(varOrValue);
    }
}

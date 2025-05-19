package uia.gerber.x2.model;

import java.util.ArrayList;
import java.util.List;

public class AM {

    private final String name;

    private final List<String> body;

    public AM(String name) {
        this.name = name;
        this.body = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<String> getBody() {
        return this.body;
    }

}

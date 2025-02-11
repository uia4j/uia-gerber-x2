package uia.gerber.x2;

import java.awt.Font;
import java.util.List;

import org.junit.Test;

import uia.gerber.x2.TextCreator;

public class TextCreatorTest {

    private static final Font FONT = new Font("Arial", Font.BOLD, 140);

    private static final char ch = '3';

    @Test
    public void testBuild() throws Exception {
        TextCreator tc = new TextCreator();
        tc.build(ch, FONT);
        tc.print();
    }

    @Test
    public void testPath() throws Exception {
        TextCreator tc = new TextCreator();
        List<List<Integer>> paths = tc.build(ch, FONT);
        tc.print();
        tc.print(paths);
    }
}

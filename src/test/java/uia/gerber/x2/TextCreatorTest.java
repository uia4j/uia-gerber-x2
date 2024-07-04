package uia.gerber.x2;

import java.awt.Font;
import java.util.List;

import org.junit.Test;

public class TextCreatorTest {

    @Test
    public void testBuild() throws Exception {
        TextCreator tc = new TextCreator();
        tc.build('/', new Font("Arial", Font.PLAIN, 140));
        tc.print();
    }

    @Test
    public void testPath() throws Exception {
        TextCreator tc = new TextCreator();
        List<List<Integer>> paths = tc.build('B', new Font("Arial", Font.PLAIN, 140));
        tc.print();
        tc.print(paths);
    }
}

package uia.gerber.x2;

public class CmdParser {

    public void read(String line) throws Exception {
        if (line.charAt(0) == '%') {
            if (line.charAt(line.length() - 1) != '%') {
                throw new Exception("ext command format is wrong");
            }
            for (String cmd : line.substring(1, line.length() - 1).split("*")) {
                handle(cmd);
            }
        }
        else {
            if (line.charAt(line.length() - 1) != '*') {
                throw new Exception("command format is wrong");
            }
            handle(line);
        }
    }

    private void handle(String cmd) {
        char c0 = cmd.charAt(0);
        char c1 = cmd.charAt(1);
        char c2 = cmd.charAt(2);
        switch (c0) {
            case 'A':

                break;
            case 'D':
                break;
            case 'F':
                break;
            case 'G':
                break;
            case 'L':
                break;
            case 'M':

                break;
            case 'S':
                break;
            case 'T':
                break;
        }
    }

    public static class Cmd {

        public String id;

        public String stmt;
    }
}

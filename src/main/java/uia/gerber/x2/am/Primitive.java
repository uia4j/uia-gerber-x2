package uia.gerber.x2.am;

public interface Primitive extends MacroBlock {

    public Code getCode();

    public enum Code {

        comment(0),

        circle(1),

        vectorLine(20),

        centerLine(21),

        outline(4),

        polygon(5),

        thermal(7);

        public final int code;

        Code(int code) {
            this.code = code;
        }

        public static Code read(int code) {
            switch (code) {
                case 0:
                    return comment;
                case 1:
                    return circle;
                case 20:
                    return vectorLine;
                case 21:
                    return centerLine;
                case 4:
                    return outline;
                case 5:
                    return polygon;
                case 7:
                    return thermal;
                default:
                    return null;
            }
        }
    }

}

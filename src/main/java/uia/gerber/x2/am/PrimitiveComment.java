package uia.gerber.x2.am;

public class PrimitiveComment implements Primitive {

    private String text;

    public PrimitiveComment() {
        this.text = "";
    }

    public PrimitiveComment(String cmd) {
        this.text = cmd.substring(2);
    }

    @Override
    public Code getCode() {
        return Code.comment;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

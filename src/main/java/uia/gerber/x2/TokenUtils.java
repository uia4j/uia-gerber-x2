package uia.gerber.x2;

import java.math.BigDecimal;

import org.antlr.v4.runtime.Token;

/**
 * Token tools.
 *
 * @author Kyle K. Lin
 *
 */
public class TokenUtils {

    public static Integer parseInt(Token v) {
        return v == null ? null : Integer.parseInt(v.getText());
    }

    public static Long parseLong(Token v) {
        return v == null ? null : Long.parseLong(v.getText());
    }

    public static BigDecimal parseDecimal(Token v) {
        return v == null ? null : new BigDecimal(v.getText());
    }
}

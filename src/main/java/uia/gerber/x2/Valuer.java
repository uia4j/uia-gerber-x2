package uia.gerber.x2;

import java.math.BigDecimal;

/**
 * The value converter for X/Y coordination based on format specification.
 *
 * @author Kyle K. Lin
 *
 */
public class Valuer {

    private final int intDigi;

    private final int decDigi;

    private final int scale;

    /**
     * The constructor.
     *
     * @param text the precision of integer and decimal.
     */
    public Valuer(String text) {
        int v = Integer.parseInt(text);
        this.intDigi = v / 10;
        this.decDigi = v % 10;
        this.scale = (int) Math.pow(10, v % 10);
    }

    /**
     * The constructor.
     *
     * @param intDigi precision for integer.
     * @param decDigi precision for decimal.
     */
    public Valuer(int intDigi, int decDigi) {
        this.intDigi = intDigi;
        this.decDigi = decDigi;
        this.scale = (int) Math.pow(10, decDigi % 10);
    }

    public int coordDigits() {
        return this.intDigi * 10 + this.decDigi;
    }

    /**
     * Returns precision for integer.
     *
     * @return precision for integer.
     */
    public int intDigi() {
        return this.intDigi;
    }

    /**
     * Returns precision for decimal.
     *
     * @return precision for decimal.
     */
    public int decDigi() {
        return this.decDigi;
    }

    /**
     * Parses the text to a number.
     *
     * <p>Suggest the precision is '46', the '87654321' will be 87.654321.</p>
     *
     * @param text The text to be parsed.
     * @return The number.
     */
    public BigDecimal in(String text) {
        return in(Long.parseLong(text));
    }

    /**
     * Parses the text to a number.
     *
     * <p>Suggest the precision is '46', the 87654321 will be 87.654321.</p>
     *
     * @param v The value to be parsed.
     * @return The number.
     */
    public BigDecimal in(long v) {
        if (v < 0) {
            long i = (-v) / this.scale;
            long d = (-v) % this.scale;
            return new BigDecimal(-i + "." + d);
        }
        else {
            long i = v / this.scale;
            long d = v % this.scale;
            return new BigDecimal(i + "." + d);
        }
    }

    /**
     * Converts a number to a text.
     *
     * <p>Suggest the precision is '46', the 87 will be '87000000'.</p>
     *
     * @param value The number.
     * @return The text.
     */
    public long out(long value) {
        return value * this.scale;
    }

    /**
     * Converts a number to a text.
     *
     * <p>Suggest the precision is '46', the 87.654321 will be '87654321'.</p>
     *
     * @param value The number.
     * @return The text.
     */
    public long out(double value) {
        return (long) (value * this.scale);
    }

    /**
     * Converts a number to a text.
     *
     * <p>Suggest the precision is '46', the 87.654321 will be '87654321'.</p>
     *
     * @param value The number.
     * @return The text.
     */
    public long out(BigDecimal value) {
        return value.multiply(BigDecimal.valueOf(this.scale)).longValue();
    }
}

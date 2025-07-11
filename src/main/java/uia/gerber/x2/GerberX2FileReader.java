package uia.gerber.x2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import uia.gerber.x2.model.AB;
import uia.gerber.x2.model.ABBlock;
import uia.gerber.x2.model.ADCircle;
import uia.gerber.x2.model.ADObound;
import uia.gerber.x2.model.ADRectangle;
import uia.gerber.x2.model.AM;
import uia.gerber.x2.model.ATTR;
import uia.gerber.x2.model.D01Plot;
import uia.gerber.x2.model.D02Move;
import uia.gerber.x2.model.D03Flash;
import uia.gerber.x2.model.Dnn;
import uia.gerber.x2.model.FS;
import uia.gerber.x2.model.G01;
import uia.gerber.x2.model.G02;
import uia.gerber.x2.model.G03;
import uia.gerber.x2.model.G04Comment;
import uia.gerber.x2.model.G36;
import uia.gerber.x2.model.G36Region;
import uia.gerber.x2.model.G37;
import uia.gerber.x2.model.G75;
import uia.gerber.x2.model.IAD;
import uia.gerber.x2.model.IOp;
import uia.gerber.x2.model.IPlot;
import uia.gerber.x2.model.IR;
import uia.gerber.x2.model.LNLayer;
import uia.gerber.x2.model.LP;
import uia.gerber.x2.model.M02;
import uia.gerber.x2.model.MO;
import uia.gerber.x2.model.MO.UnitType;

@SuppressWarnings("deprecation")
public class GerberX2FileReader {

    private final GerberX2FileReaderListener listener;

    private int lineNo;

    private AM am;

    private ABBlock ab;

    private G36Region g36;

    private G36Region.Contour contour;

    private FS fs;

    private MO mo;

    private LNLayer layer;

    public GerberX2FileReader() {
        this.listener = new GerberX2FileReaderListener() {
        };
    }

    public GerberX2FileReader(GerberX2FileReaderListener listener) {
        this.listener = listener;
    }

    public synchronized FormatMode findFormatMode(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            return findFormatMode(fis);
        }
    }

    public synchronized FormatMode findFormatMode(InputStream fis) throws IOException {
        this.lineNo = 0;
        this.ab = null;
        this.g36 = null;
        this.contour = null;
        this.fs = null;
        this.layer = null;

        // remark:
        //  Files.lines() failed
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"))) {
            for (String line = null; (line = br.readLine()) != null;) {
                handle(line);
                if (this.fs != null && this.mo != null) {
                    return new FormatMode(this.fs, this.mo);
                }
            }
        }
        return null;
    }

    public synchronized void run(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            run(fis);
        }
    }

    public synchronized void run(InputStream fis) throws IOException {
        this.lineNo = 0;
        this.ab = null;
        this.g36 = null;
        this.contour = null;
        this.fs = null;
        this.layer = null;

        // remark:
        //  Files.lines() failed
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"))) {
            for (String line = null; (line = br.readLine()) != null;) {
                handle(line);
            }
        }
        if (this.listener != null) {
            this.listener.eof();
        }
    }

    public synchronized boolean run(InputStream fis, Cmder cmder) throws IOException {
        if (cmder == null) {
            run(fis);
            return true;
        }

        this.lineNo = 0;
        this.ab = null;
        this.g36 = null;
        this.contour = null;
        this.fs = null;
        this.layer = null;

        // remark:
        //  Files.lines() failed
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"))) {
            for (String line = null; (line = br.readLine()) != null;) {
                handle(line);
                if (cmder.cancel()) {
                    return false;
                }
            }
        }
        if (this.listener != null) {
            this.listener.eof();
        }

        return true;
    }

    private void handle(String line) {
        this.lineNo++;
        int len = line.length();
        if (len == 0) {
            return;
        }

        if (this.am != null) {
            GerberX2Statement stmt = new GerberX2Statement.LINE(line);
            this.listener.enter(this.lineNo, stmt);

            if (line.charAt(0) == '%') {
                this.listener.enterAM(this.lineNo, this.am);
                this.am = null;
            }
            else {
                this.am.getBody().add(line);
            }
            return;
        }

        if (line.charAt(0) == '%') {
            if (len == 1) {
                this.listener.enter(this.lineNo, new GerberX2Statement.LINE(line));
                return;
            }

            if (!line.startsWith("AM")) {
                if (line.charAt(len - 1) != '%') {
                    this.listener.error(this.lineNo, line, new Exception("ExtCmd format is not correct"));
                    this.listener.enter(this.lineNo, new GerberX2Statement.LINE(line));
                    return;
                }
            }

            for (String cmd : line.substring(1, len - 1).split("\\*")) {
                try {
                    exec(cmd, true);
                }
                catch (Exception ex) {
                    this.listener.error(len, cmd, ex);
                    ex.printStackTrace();
                }

            }
        }
        else {
            if (line.charAt(len - 1) != '*') {
                this.listener.error(this.lineNo, line, new Exception("Cmd format is not correct"));
                this.listener.enter(this.lineNo, new GerberX2Statement.LINE(line));
                return;
            }

            try {
                exec(line.substring(0, len - 1), false);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void exec(String cmd, boolean ext) throws Exception {
        if (cmd == null || cmd.isEmpty()) {
            return;
        }

        GerberX2Statement stmt = null;
        char ch = cmd.charAt(0);
        String next = null;

        if (ch == 'G') {                                            // G
            if (cmd.startsWith("G01")) {                            // G01
                stmt = new G01(cmd.length() == 3);
                if (this.contour != null) {
                    this.contour.plot((G01) stmt);                  // G36, G01
                }
                this.listener.plot(this.lineNo, (IPlot) stmt);
                if (cmd.length() > 3) {
                    next = cmd.substring(3);
                }
            }
            else if (cmd.startsWith("G02")) {                       // G02
                stmt = new G02(cmd.length() == 3);
                if (this.contour != null) {
                    this.contour.plot((G02) stmt);                  // G36, G02
                }
                this.listener.plot(this.lineNo, (IPlot) stmt);
                if (cmd.length() > 3) {
                    next = cmd.substring(3);
                }
            }
            else if (cmd.startsWith("G03")) {                       // G03
                stmt = new G03(cmd.length() == 3);
                if (this.contour != null) {
                    this.contour.plot((G03) stmt);                  // G36, G03
                }
                this.listener.plot(this.lineNo, (IPlot) stmt);
                if (cmd.length() > 3) {
                    next = cmd.substring(3);
                }
            }
            else if (cmd.startsWith("G04")) {                       // G04
                stmt = new G04Comment(cmd.substring(3));
            }
            else if (cmd.equals("G36")) {                           // G36
                stmt = new G36();
                this.listener.enterG36(this.lineNo);
                this.g36 = new G36Region();
                this.contour = null;
            }
            else if (cmd.equals("G37")) {                           // G37
                stmt = new G37();
                this.listener.exitG36(this.lineNo, this.g36);
                this.g36 = null;
                this.contour = null;
            }
            else if (cmd.equals("G75")) {                           // G75
                stmt = new G75();
            }
        }
        else if (ch == 'D') {                                       // D
            stmt = new Dnn(Integer.parseInt(cmd.substring(1)), false);
        }
        else if (ch == 'L') {                                       // L
            if (cmd.equals("LPD")) {                                // LPD
                stmt = new LP(true);
            }
            else if (cmd.equals("LPC")) {                           // LPC
                stmt = new LP(false);
            }
            else if (cmd.startsWith("LN")) {                        // LN
                stmt = new LNLayer(cmd.substring(2));
                if (this.layer != null) {
                    this.listener.exitLayer(this.lineNo - 1, this.layer);
                }
                this.layer = (LNLayer) stmt;
                this.listener.enterLayer(this.lineNo, this.layer);
            }
        }
        else if (ch == 'A') {                                       // A
            if (cmd.startsWith("AB")) {                             // AB
                if (cmd.length() > 2) {
                    String dxx = cmd.substring(2);
                    stmt = new AB(dxx);
                    this.ab = new ABBlock(dxx);
                    this.listener.apertureDefined(this.lineNo, (IAD) stmt);
                    this.listener.enterAB(this.lineNo, (AB) stmt);
                }
                else {
                    this.listener.exitAB(this.lineNo, this.ab);
                    this.ab = null;
                }
            }
            else if (cmd.startsWith("ADD")) {                       // ADD
                String[] shapeParam = cmd.split("[,X]");

                String dxx = shapeParam[0].substring(3, shapeParam[0].length() - 1);
                int nCode = Integer.parseInt(dxx);
                char sn = shapeParam[0].charAt(shapeParam[0].length() - 1);

                if (sn == 'C') {
                    stmt = new ADCircle(
                            nCode,
                            new BigDecimal(shapeParam[1]),
                            shapeParam.length > 2 ? new BigDecimal(shapeParam[2]) : null);
                }
                else if (sn == 'R') {
                    stmt = new ADRectangle(
                            nCode,
                            new BigDecimal(shapeParam[1]),
                            new BigDecimal(shapeParam[2]),
                            shapeParam.length > 3 ? new BigDecimal(shapeParam[3]) : null);
                }
                else if (sn == 'O') {
                    stmt = new ADObound(
                            nCode,
                            new BigDecimal(shapeParam[1]),
                            new BigDecimal(shapeParam[2]),
                            shapeParam.length > 3 ? new BigDecimal(shapeParam[3]) : null);
                }
                this.listener.apertureDefined(this.lineNo, (IAD) stmt);
            }
            else if (cmd.startsWith("AM")) {                        // AM
                String name = cmd.substring(2);
                this.am = new AM(name);
            }
            // TODO:
        }
        else if (ch == 'T') {                                       // T
            ATTR attr = new ATTR(cmd.substring(0, 2));
            String[] ps = cmd.substring(3).split(",");
            attr.setName(ps[0]);
            for (int i = 1; i < ps.length; i++) {
                attr.getFields().add(ps[i]);
            }
            stmt = attr;
        }
        else if (cmd.startsWith("IR")) {                             // IR
            stmt = new IR(Integer.parseInt(cmd.substring(2)));
        }
        else if (cmd.endsWith("D01")) {                             // D01
            Long[] xyij = dxx(cmd);
            stmt = new D01Plot(xyij[0], xyij[1], xyij[2], xyij[3]);
            if (this.contour != null) {
                this.contour.plot((D01Plot) stmt);                  // G36, D01
            }
            this.listener.op(this.lineNo, (IOp) stmt);
        }
        else if (cmd.endsWith("D02")) {                             // D02
            Long[] xy = dxx(cmd);
            stmt = new D02Move(xy[0], xy[1]);
            if (this.g36 != null) {
                this.contour = this.g36.create((D02Move) stmt);     // G36, D02, create a new contour object.
            }
            this.listener.op(this.lineNo, (IOp) stmt);
        }
        else if (cmd.endsWith("D03")) {                             // D03
            Long[] xy = dxx(cmd);
            stmt = new D03Flash(xy[0], xy[1]);
            this.listener.op(this.lineNo, (IOp) stmt);
        }
        else if (cmd.equals("M02")) {                               // M02
            if (this.layer != null) {
                this.listener.exitLayer(this.lineNo - 1, this.layer);
            }
            this.listener.beforeEnd(this.lineNo);
            stmt = new M02();
        }
        else if (cmd.startsWith("MO")) {                            // MO
            this.mo = new MO(UnitType.valueOf(cmd.substring(2)));
            stmt = this.mo;
            this.listener.mo(this.lineNo, this.mo);
        }
        else if (cmd.startsWith("FS")) {                            // FS
            Integer[] xy = fs(cmd);
            this.fs = new FS(xy[0] / 10, xy[0] % 10);
            this.listener.fs(this.lineNo, this.fs);
            stmt = this.fs;
        }

        if (stmt == null) {
            stmt = new GerberX2Statement.UNK(cmd, ext);
            this.listener.unknown(this.lineNo, cmd);
        }

        if (this.ab != null && !(stmt instanceof AB)) {
            this.ab.stmts.add(stmt);
        }
        this.listener.enter(this.lineNo, stmt);

        if (next != null) {
            exec(next, ext);
        }
    }

    private Long[] dxx(String data) {
        String[] xyij = new String[] {
                "",
                "",
                "",
                ""
        };
        int selected = -1;
        int d = data.indexOf("D");
        char[] chs = data.toCharArray();
        for (int p = 0; p < d; p++) {
            if (chs[p] == 'X') {
                selected = 0;
            }
            else if (chs[p] == 'Y') {
                selected = 1;
            }
            else if (chs[p] == 'I') {
                selected = 2;
            }
            else if (chs[p] == 'J') {
                selected = 3;
            }
            else {
                xyij[selected] = (xyij[selected] + chs[p]);
            }
        }

        Long[] result = new Long[4];
        for (int p = 0; p < 4; p++) {
            if (!xyij[p].equals("")) {
                result[p] = Long.parseLong(xyij[p]);
            }
        }
        return result;
    }

    private Integer[] fs(String data) {
        String[] xy = new String[] {
                "",
                ""
        };
        int selected = -1;
        char[] chs = data.toCharArray();
        for (int p = 4; p < chs.length; p++) {
            if (chs[p] == 'X') {
                selected = 0;
            }
            else if (chs[p] == 'Y') {
                selected = 1;
            }
            else {
                xy[selected] = (xy[selected] + chs[p]);
            }
        }

        Integer[] result = new Integer[2];
        for (int p = 0; p < 2; p++) {
            if (!xy[p].equals("")) {
                result[p] = Integer.parseInt(xy[p]);
            }
        }
        return result;
    }

    public static interface Cmder {

        public boolean cancel();
    }

    public static class FormatMode {

        public final FS format;

        public final MO mode;

        public FormatMode(FS fs, MO mode) {
            this.format = fs;
            this.mode = mode;
        }
    }
}

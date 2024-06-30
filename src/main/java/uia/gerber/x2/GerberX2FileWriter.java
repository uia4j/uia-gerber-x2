package uia.gerber.x2;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import uia.gerber.x2.builder.CommonGraphics;
import uia.gerber.x2.model.ATTR;
import uia.gerber.x2.model.FS;
import uia.gerber.x2.model.G01;
import uia.gerber.x2.model.G04Comment;
import uia.gerber.x2.model.G75;
import uia.gerber.x2.model.LP;
import uia.gerber.x2.model.MO;

/**
 * The Gerber X2 layout file writer.
 *
 * @author Kyle K. Lin
 *
 */
public class GerberX2FileWriter {

    private String description;

    private MO.UnitType unit;

    private Valuer xValuer;

    private Valuer yValuer;

    private OutputStream out;

    private int step;

    private List<Integer> ad;

    private CommonGraphics graphics;

    private List<ATTR> attrs;

    public GerberX2FileWriter(OutputStream out) {
        this.unit = MO.UnitType.MM;
        this.xValuer = new Valuer(4, 6);
        this.yValuer = new Valuer(4, 6);
        this.out = out;
        this.ad = new ArrayList<>();
        this.attrs = new ArrayList<ATTR>();
    }

    public void addAttribute(String name, String... fields) {
        ATTR attr = new ATTR("TF");
        attr.setName(name);
        for (String f : fields) {
            attr.getFields().add(f);
        }
        this.attrs.add(attr);
    }

    public OutputStream getOutputStream() {
        return this.out;
    }

    public void setDescription(String description) {
        if (this.step == 0) {
            this.description = description;
        }
    }

    public void setUnit(MO.UnitType unit) {
        if (this.step == 0) {
            this.unit = unit;
        }
    }

    public void setXValuer(int xIntDigi, int xDecDigi) {
        if (this.step == 0) {
            this.xValuer = new Valuer(xIntDigi, xDecDigi);
        }
    }

    public void setYValuer(int yIntDigi, int yDecDigi) {
        if (this.step == 0) {
            this.yValuer = new Valuer(yIntDigi, yDecDigi);
        }
    }

    public Valuer x() {
        return this.xValuer;
    }

    public Valuer y() {
        return this.yValuer;
    }

    public Long x(long v) {
        return this.xValuer.out(v);
    }

    public Long x(double v) {
        return this.xValuer.out(v);
    }

    public Long x(BigDecimal v) {
        return this.xValuer.out(v);
    }

    public Long y(long v) {
        return this.yValuer.out(v);
    }

    public Long y(double v) {
        return this.yValuer.out(v);
    }

    public Long y(BigDecimal v) {
        return this.yValuer.out(v);
    }

    public boolean contains(int no) {
        return this.ad.contains(no);
    }

    public boolean addDnn(int no) {
        if (this.ad.contains(no)) {
            return false;
        }

        this.ad.add(no);
        return true;
    }

    /**
     *
     * @return 0: pass, 1: started already, 2: closed.
     * @throws IOException
     */
    public int start() throws IOException {
        if (this.step != 0) {
            return this.step;
        }

        new G04Comment(this.description).write(this.out);
        for (ATTR attr : this.attrs) {
            attr.write(this.out);
        }
        new FS(this.xValuer, this.yValuer).write(this.out);
        new MO(this.unit).write(this.out);
        new LP("D").write(this.out);
        new G01().write(this.out);
        new G75().write(this.out);

        this.step = 1;
        this.graphics = new CommonGraphics(this);
        return 0;
    }

    public CommonGraphics getGraphics() {
        return this.graphics;
    }

    /**
     *
     * @return 0: pass, 1: not started, 2: closed.
     * @throws IOException
     */
    public int stop() throws IOException {
        if (this.step == 0) {
            return 1;
        }
        if (this.step == 2) {
            return 2;
        }
        this.graphics.close();
        this.out.write("M02*\n".getBytes());
        this.step = 2;
        return 0;
    }
}

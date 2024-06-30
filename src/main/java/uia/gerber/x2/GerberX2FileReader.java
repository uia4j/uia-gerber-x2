package uia.gerber.x2;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import uia.gerber.x2.model.AB;
import uia.gerber.x2.model.AD;
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
import uia.gerber.x2.model.G36Region;
import uia.gerber.x2.model.G36Region.Contour;
import uia.gerber.x2.model.G75;
import uia.gerber.x2.model.LM;
import uia.gerber.x2.model.LP;
import uia.gerber.x2.model.LR;
import uia.gerber.x2.model.LS;
import uia.gerber.x2.model.MO;
import uia.gerber.x2.model.SR;

/**
 * The Gerber X2 layout file reader.
 *
 * @author Kyle K. Lin
 *
 */
public class GerberX2FileReader {

    private MO mo;

    private FS fs;

    private boolean inRegion;

    private G36Region currRegion;

    private SR currStepRepeat;

    private List<AB> blocks;

    private List<ATTR> attrs;

    private GerberX2Visitor visitor;

    public GerberX2FileReader() {
        this.blocks = new ArrayList<>();
        this.attrs = new ArrayList<>();
    }

    public GerberX2FileReader(GerberX2Visitor visitor) {
        this.visitor = visitor;
        this.blocks = new ArrayList<>();
        this.attrs = new ArrayList<>();
    }

    public void run(Path path) throws IOException {
        this.mo = null;
        this.fs = null;
        this.inRegion = false;
        this.currRegion = null;
        this.currStepRepeat = null;
        this.blocks.clear();

        final GerberX2Lexer lexer = new GerberX2Lexer(CharStreams.fromPath(path));
        final GerberX2Parser parser = new GerberX2Parser(new CommonTokenStream(lexer));
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new GerberX2BaseListener() {

            @Override
            public void enterMo(GerberX2Parser.MoContext ctx) {
                GerberX2FileReader.this.mo = new MO(MO.UnitType.valueOf(ctx.unit.getText()));
                acceptStmt(GerberX2FileReader.this.mo);
            }

            @Override
            public void enterFs(GerberX2Parser.FsContext ctx) {
                GerberX2FileReader.this.fs = new FS(new Valuer(ctx.x.getText()), new Valuer(ctx.y.getText()));
                acceptStmt(GerberX2FileReader.this.fs);
            }

            @Override
            public void enterG36(GerberX2Parser.G36Context ctx) {
                GerberX2FileReader.this.inRegion = true;
            }

            @Override
            public void exitG36(GerberX2Parser.G36Context ctx) {
                acceptStmt(GerberX2FileReader.this.currRegion);
                GerberX2FileReader.this.inRegion = false;
                GerberX2FileReader.this.currRegion = null;
            }

            @Override
            public void enterG36Region(GerberX2Parser.G36RegionContext ctx) {
            }

            @Override
            public void enterG01(GerberX2Parser.G01Context ctx) {
                String g = ctx.getText();
                if (GerberX2FileReader.this.inRegion) {
                    GerberX2FileReader.this.currRegion.contours.add(new G36Region.Contour(g));
                }
                else {
                    acceptStmt(new G01());
                }
            }

            @Override
            public void enterG02(GerberX2Parser.G02Context ctx) {
                String g = ctx.getText();
                if (GerberX2FileReader.this.inRegion) {
                    GerberX2FileReader.this.currRegion.contours.add(new G36Region.Contour(g));
                }
                else {
                    acceptStmt(new G02());
                }
            }

            @Override
            public void enterG03(GerberX2Parser.G03Context ctx) {
                String g = ctx.getText();
                if (GerberX2FileReader.this.inRegion) {
                    GerberX2FileReader.this.currRegion.contours.add(new G36Region.Contour(g));
                }
                else {
                    acceptStmt(new G03());
                }
            }

            @Override
            public void enterG04(GerberX2Parser.G04Context ctx) {
                acceptStmt(new G04Comment(ctx.comment.getText()));
            }

            @Override
            public void enterG75(GerberX2Parser.G75Context ctx) {
                acceptStmt(new G75());
            }

            @Override
            public void enterAd(GerberX2Parser.AdContext ctx) {
                AD ad = new AD();
                ad.setAttributes(GerberX2FileReader.this.attrs);
                ad.setDnn(ctx.d.getText());
                ad.setTemplate(ctx.template.getText());
                String[] xs = ctx.getText().split(",")[1].split("X");
                for (String x : xs) {
                    ad.getXs().add(new BigDecimal(x));
                }
                GerberX2FileReader.this.attrs.clear();
                acceptStmt(ad);
            }

            @Override
            public void enterD01(GerberX2Parser.D01Context ctx) {
                D01Plot d01 = new D01Plot(ctx.g, ctx.x, ctx.y, ctx.i, ctx.j);
                if (GerberX2FileReader.this.inRegion) {
                    GerberX2FileReader.this.currRegion.contours.add(new Contour(d01));
                }
                else {
                    acceptStmt(d01);
                }
            }

            @Override
            public void enterD02(GerberX2Parser.D02Context ctx) {
                D02Move d02 = new D02Move(ctx.x, ctx.y);
                if (GerberX2FileReader.this.inRegion) {
                    GerberX2FileReader.this.currRegion = new G36Region(d02);
                    GerberX2FileReader.this.currRegion.setAttributes(GerberX2FileReader.this.attrs);
                    GerberX2FileReader.this.attrs.clear();
                }
                else {
                    acceptStmt(d02);
                }
            }

            @Override
            public void enterD03(GerberX2Parser.D03Context ctx) {
                acceptStmt(new D03Flash(ctx.x, ctx.y));
            }

            @Override
            public void enterDnn(GerberX2Parser.DnnContext ctx) {
                acceptStmt(new Dnn(ctx.d));
            }

            @Override
            public void enterLp(GerberX2Parser.LpContext ctx) {
                acceptStmt(new LP(ctx.polarity.getText()));
            }

            @Override
            public void enterLm(GerberX2Parser.LmContext ctx) {
                acceptStmt(new LM(LM.MrriorType.valueOf(ctx.mirror.getText())));
            }

            @Override
            public void enterLr(GerberX2Parser.LrContext ctx) {
                acceptStmt(new LR(TokenUtils.parseDecimal(ctx.degree)));
            }

            @Override
            public void enterLs(GerberX2Parser.LsContext ctx) {
                acceptStmt(new LS(TokenUtils.parseDecimal(ctx.scale)));
            }

            @Override
            public void visitErrorNode(ErrorNode node) {
                acceptError(node.toString());
            }

            @Override
            public void enterTf(GerberX2Parser.TfContext ctx) {
                ATTR attr = new ATTR("TF");
                attr.setName(ctx.t.getText().substring(2));
                ctx.field().forEach(f -> {
                    attr.getFields().add(f.getText());
                });
                acceptStmt(attr);
            }

            @Override
            public void enterTa(GerberX2Parser.TaContext ctx) {
                ATTR attr = new ATTR("TA");
                attr.setName(ctx.t.getText().substring(2));
                ctx.field().forEach(f -> {
                    attr.getFields().add(f.getText());
                });
                GerberX2FileReader.this.attrs.add(attr);
                // DO NOT call acceptStmt;
                // acceptStmt(attr);
            }

            @Override
            public void enterTo(GerberX2Parser.ToContext ctx) {
                ATTR attr = new ATTR("TO");
                attr.setName(ctx.t.getText().substring(2));
                ctx.field().forEach(f -> {
                    attr.getFields().add(f.getText());
                });
                // DO NOT call acceptStmt;
                // acceptStmt(attr);
            }

            @Override
            public void enterTd(GerberX2Parser.TdContext ctx) {
                ATTR attr = new ATTR("TD");
                attr.setName(ctx.t.getText().substring(2));
                acceptStmt(attr);
            }

            @Override
            public void enterSr(GerberX2Parser.SrContext ctx) {
                if (ctx.x != null) {
                    GerberX2FileReader.this.currStepRepeat = new SR(ctx.x, ctx.y, ctx.i, ctx.j);
                }
                else {
                    SR curr = GerberX2FileReader.this.currStepRepeat;
                    GerberX2FileReader.this.currStepRepeat = null;
                    acceptStmt(curr);
                }
            }

            @Override
            public void enterAb(GerberX2Parser.AbContext ctx) {
                AB curr = GerberX2FileReader.this.blocks.isEmpty()
                        ? null
                        : GerberX2FileReader.this.blocks.get(0);

                if (ctx.d != null) {
                    AB ab = new AB(ctx.d.getText());
                    GerberX2FileReader.this.blocks.add(0, ab);
                    if (curr != null) {
                        curr.stmts.add(ab);
                    }
                }
                else {
                    GerberX2FileReader.this.blocks.remove(0);
                    if (GerberX2FileReader.this.blocks.isEmpty()) {
                        acceptStmt(curr);
                    }
                }
            }

        }, parser.gerberX2());

        acceptCmd("M02");
    }

    private void acceptStmt(GerberX2Statement stmt) {
        // SR
        if (this.currStepRepeat != null) {
            this.currStepRepeat.stmts.add(stmt);
            return;
        }
        // AB
        if (!this.blocks.isEmpty()) {
            AB ab = this.blocks.get(0);
            ab.stmts.add(stmt);
            return;
        }

        if (this.visitor != null) {
            this.visitor.stat(stmt);
        }
    }

    private void acceptCmd(String stmt) {
        if (this.visitor != null) {
            this.visitor.cmd(stmt);
        }
    }

    private void acceptError(String stmt) {
        if (this.visitor != null) {
            this.visitor.error(stmt);
        }
    }
}

package uia.gerber.x2.font;

import java.util.ArrayList;
import java.util.List;

import uia.gerber.x2.font.arial.D032Space;
import uia.gerber.x2.font.arial.D033EM;
import uia.gerber.x2.font.arial.D034DQ;
import uia.gerber.x2.font.arial.D035Sharp;
import uia.gerber.x2.font.arial.D036Dollar;
import uia.gerber.x2.font.arial.D037Pct;
import uia.gerber.x2.font.arial.D038And;
import uia.gerber.x2.font.arial.D039SQ;
import uia.gerber.x2.font.arial.D040LRB;
import uia.gerber.x2.font.arial.D041RRB;
import uia.gerber.x2.font.arial.D042Star;
import uia.gerber.x2.font.arial.D043Plus;
import uia.gerber.x2.font.arial.D044Comma;
import uia.gerber.x2.font.arial.D045Dash;
import uia.gerber.x2.font.arial.D046Dot;
import uia.gerber.x2.font.arial.D047Slash;
import uia.gerber.x2.font.arial.D0480;
import uia.gerber.x2.font.arial.D0491;
import uia.gerber.x2.font.arial.D0502;
import uia.gerber.x2.font.arial.D0513;
import uia.gerber.x2.font.arial.D0524;
import uia.gerber.x2.font.arial.D0535;
import uia.gerber.x2.font.arial.D0546;
import uia.gerber.x2.font.arial.D0557;
import uia.gerber.x2.font.arial.D0568;
import uia.gerber.x2.font.arial.D0579;
import uia.gerber.x2.font.arial.D058Colon;
import uia.gerber.x2.font.arial.D059Semicolon;
import uia.gerber.x2.font.arial.D060LT;
import uia.gerber.x2.font.arial.D061Equal;
import uia.gerber.x2.font.arial.D062GT;
import uia.gerber.x2.font.arial.D063Question;
import uia.gerber.x2.font.arial.D064At;
import uia.gerber.x2.font.arial.D065A;
import uia.gerber.x2.font.arial.D066B;
import uia.gerber.x2.font.arial.D067C;
import uia.gerber.x2.font.arial.D068D;
import uia.gerber.x2.font.arial.D069E;
import uia.gerber.x2.font.arial.D070F;
import uia.gerber.x2.font.arial.D071G;
import uia.gerber.x2.font.arial.D072H;
import uia.gerber.x2.font.arial.D073I;
import uia.gerber.x2.font.arial.D074J;
import uia.gerber.x2.font.arial.D075K;
import uia.gerber.x2.font.arial.D076L;
import uia.gerber.x2.font.arial.D077M;
import uia.gerber.x2.font.arial.D078N;
import uia.gerber.x2.font.arial.D079O;
import uia.gerber.x2.font.arial.D080P;
import uia.gerber.x2.font.arial.D081Q;
import uia.gerber.x2.font.arial.D082R;
import uia.gerber.x2.font.arial.D083S;
import uia.gerber.x2.font.arial.D084T;
import uia.gerber.x2.font.arial.D085U;
import uia.gerber.x2.font.arial.D086V;
import uia.gerber.x2.font.arial.D087W;
import uia.gerber.x2.font.arial.D088X;
import uia.gerber.x2.font.arial.D089Y;
import uia.gerber.x2.font.arial.D090Z;
import uia.gerber.x2.font.arial.D091LSB;
import uia.gerber.x2.font.arial.D092BackSlash;
import uia.gerber.x2.font.arial.D093RSB;
import uia.gerber.x2.font.arial.D094;
import uia.gerber.x2.font.arial.D095UnderScore;
import uia.gerber.x2.font.arial.D096;
import uia.gerber.x2.font.arial.D097a;
import uia.gerber.x2.font.arial.D098b;
import uia.gerber.x2.font.arial.D099c;
import uia.gerber.x2.font.arial.D100d;
import uia.gerber.x2.font.arial.D101e;
import uia.gerber.x2.font.arial.D102f;
import uia.gerber.x2.font.arial.D103g;
import uia.gerber.x2.font.arial.D104h;
import uia.gerber.x2.font.arial.D105i;
import uia.gerber.x2.font.arial.D106j;
import uia.gerber.x2.font.arial.D107k;
import uia.gerber.x2.font.arial.D108l;
import uia.gerber.x2.font.arial.D109m;
import uia.gerber.x2.font.arial.D110n;
import uia.gerber.x2.font.arial.D111o;
import uia.gerber.x2.font.arial.D112p;
import uia.gerber.x2.font.arial.D113q;
import uia.gerber.x2.font.arial.D114r;
import uia.gerber.x2.font.arial.D115s;
import uia.gerber.x2.font.arial.D116t;
import uia.gerber.x2.font.arial.D117u;
import uia.gerber.x2.font.arial.D118v;
import uia.gerber.x2.font.arial.D119w;
import uia.gerber.x2.font.arial.D120x;
import uia.gerber.x2.font.arial.D121y;
import uia.gerber.x2.font.arial.D122z;
import uia.gerber.x2.font.arial.D123LCB;
import uia.gerber.x2.font.arial.D124;
import uia.gerber.x2.font.arial.D125RCB;
import uia.gerber.x2.font.arial.D126;

/**
 * new Font("Arial", Font.PLAIN, 140)
 *
 * @author Kyle K. Lin
 *
 */
public class Arial {

    public static final ASCII[] ascii = new ASCII[128];

    static {
        ascii[(byte) ' '] = new D032Space();
        ascii[(byte) '!'] = new D033EM();
        ascii[(byte) '"'] = new D034DQ();
        ascii[(byte) '#'] = new D035Sharp();
        ascii[(byte) '$'] = new D036Dollar();
        ascii[(byte) '%'] = new D037Pct();
        ascii[(byte) '&'] = new D038And();
        ascii[(byte) '\''] = new D039SQ();
        ascii[(byte) '('] = new D040LRB();
        ascii[(byte) ')'] = new D041RRB();
        ascii[(byte) '*'] = new D042Star();
        ascii[(byte) '+'] = new D043Plus();
        ascii[(byte) ','] = new D044Comma();
        ascii[(byte) '-'] = new D045Dash();
        ascii[(byte) '.'] = new D046Dot();
        ascii[(byte) '/'] = new D047Slash();
        ascii[(byte) '0'] = new D0480();
        ascii[(byte) '1'] = new D0491();
        ascii[(byte) '2'] = new D0502();
        ascii[(byte) '3'] = new D0513();
        ascii[(byte) '4'] = new D0524();
        ascii[(byte) '5'] = new D0535();
        ascii[(byte) '6'] = new D0546();
        ascii[(byte) '7'] = new D0557();
        ascii[(byte) '8'] = new D0568();
        ascii[(byte) '9'] = new D0579();
        ascii[(byte) ':'] = new D058Colon();
        ascii[(byte) ';'] = new D059Semicolon();
        ascii[(byte) '<'] = new D060LT();
        ascii[(byte) '='] = new D061Equal();
        ascii[(byte) '>'] = new D062GT();
        ascii[(byte) '?'] = new D063Question();
        ascii[(byte) '@'] = new D064At();
        ascii[(byte) 'A'] = new D065A();
        ascii[(byte) 'B'] = new D066B();
        ascii[(byte) 'C'] = new D067C();
        ascii[(byte) 'D'] = new D068D();
        ascii[(byte) 'E'] = new D069E();
        ascii[(byte) 'F'] = new D070F();
        ascii[(byte) 'G'] = new D071G();
        ascii[(byte) 'H'] = new D072H();
        ascii[(byte) 'I'] = new D073I();
        ascii[(byte) 'J'] = new D074J();
        ascii[(byte) 'K'] = new D075K();
        ascii[(byte) 'L'] = new D076L();
        ascii[(byte) 'M'] = new D077M();
        ascii[(byte) 'N'] = new D078N();
        ascii[(byte) 'O'] = new D079O();
        ascii[(byte) 'P'] = new D080P();
        ascii[(byte) 'Q'] = new D081Q();
        ascii[(byte) 'R'] = new D082R();
        ascii[(byte) 'S'] = new D083S();
        ascii[(byte) 'T'] = new D084T();
        ascii[(byte) 'U'] = new D085U();
        ascii[(byte) 'V'] = new D086V();
        ascii[(byte) 'W'] = new D087W();
        ascii[(byte) 'X'] = new D088X();
        ascii[(byte) 'Y'] = new D089Y();
        ascii[(byte) 'Z'] = new D090Z();
        ascii[(byte) '['] = new D091LSB();
        ascii[(byte) '\\'] = new D092BackSlash();
        ascii[(byte) ']'] = new D093RSB();
        ascii[(byte) '^'] = new D094();
        ascii[(byte) '_'] = new D095UnderScore();
        ascii[(byte) '`'] = new D096();
        ascii[(byte) 'a'] = new D097a();
        ascii[(byte) 'b'] = new D098b();
        ascii[(byte) 'c'] = new D099c();
        ascii[(byte) 'd'] = new D100d();
        ascii[(byte) 'e'] = new D101e();
        ascii[(byte) 'f'] = new D102f();
        ascii[(byte) 'g'] = new D103g();
        ascii[(byte) 'h'] = new D104h();
        ascii[(byte) 'i'] = new D105i();
        ascii[(byte) 'j'] = new D106j();
        ascii[(byte) 'k'] = new D107k();
        ascii[(byte) 'l'] = new D108l();
        ascii[(byte) 'm'] = new D109m();
        ascii[(byte) 'n'] = new D110n();
        ascii[(byte) 'o'] = new D111o();
        ascii[(byte) 'p'] = new D112p();
        ascii[(byte) 'q'] = new D113q();
        ascii[(byte) 'r'] = new D114r();
        ascii[(byte) 's'] = new D115s();
        ascii[(byte) 't'] = new D116t();
        ascii[(byte) 'u'] = new D117u();
        ascii[(byte) 'v'] = new D118v();
        ascii[(byte) 'w'] = new D119w();
        ascii[(byte) 'x'] = new D120x();
        ascii[(byte) 'y'] = new D121y();
        ascii[(byte) 'z'] = new D122z();
        ascii[(byte) '{'] = new D123LCB();
        ascii[(byte) '|'] = new D124();
        ascii[(byte) '}'] = new D125RCB();
        ascii[(byte) '~'] = new D126();
    }

    public static ASCII ch(char ch) {
        return ascii[(byte) ch];
    }

    public static List<ASCII> text(String text) {
        ArrayList<ASCII> result = new ArrayList<>();
        for (char ch : text.toCharArray()) {
            result.add(ch(ch));
        }
        return result;
    }
}

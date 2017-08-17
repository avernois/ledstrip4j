package fr.craftinglabs.pi.apa102.text;

import fr.craftinglabs.pi.apa102.font.Font;
import fr.craftinglabs.pi.apa102.font.Glyph;
import fr.craftinglabs.pi.apa102.matrix.Size;

import java.util.Arrays;

public class TextFont {
    private static Size glyphSize = new Size(3, 5);
    private static final Glyph ONE = new Glyph(glyphSize, '1',
            " X ",
            " X ",
            " X ",
            " X ",
            " X ");

    private static final Glyph TWO = new Glyph(glyphSize, '2',
            "XXX",
            "  X",
            "XXX",
            "X  ",
            "XXX");
    private static final Glyph THREE = new Glyph(glyphSize, '3',
            "XXX",
            "  X",
            "XXX",
            "  X",
            "XXX");

    private static final Glyph FOUR = new Glyph(glyphSize, '4',
            "X X",
            "X X",
            "XXX",
            "  X",
            "  X");
    private static final Glyph FIVE = new Glyph(glyphSize, '5',
            "XXX",
            "X  ",
            "XXX",
            "  X",
            "XXX");

    private static final Glyph SIX = new Glyph(glyphSize, '6',
            "XXX",
            "X  ",
            "XXX",
            "X X",
            "XXX");

    private static final Glyph SEVEN = new Glyph(glyphSize, '7',
            "XXX",
            "  X",
            "  X",
            "  X",
            "  X");

    private static final Glyph EIGHT = new Glyph(glyphSize, '8',
            "XXX",
            "X X",
            "XXX",
            "X X",
            "XXX");

    private static final Glyph NINE = new Glyph(glyphSize, '9',
            "XXX",
            "X X",
            "XXX",
            "  X",
            "XXX");

    private static final Glyph ZERO = new Glyph(glyphSize, '0',
            "XXX",
            "X X",
            "X X",
            "X X",
            "XXX");

    private static final Glyph SPACE = new Glyph(glyphSize, ' ',
            "   ",
            "   ",
            "   ",
            "   ",
            "   ");

    private static final Glyph A = new Glyph(glyphSize, 'A',
            "XXX",
            "X X",
            "X X",
            "XXX",
            "X X");

    private static final Glyph B = new Glyph(glyphSize, 'B',
            "XX ",
            "X X",
            "XX ",
            "X X",
            "XX ");
    private static final Glyph C = new Glyph(glyphSize, 'C',
            "XXX",
            "X  ",
            "X  ",
            "X  ",
            "XXX");

    private static final Glyph D = new Glyph(glyphSize, 'D',
            "XX ",
            "X X",
            "X X",
            "X X",
            "XX ");

    private static final Glyph E = new Glyph(glyphSize, 'E',
            "XXX",
            "X  ",
            "XX ",
            "X  ",
            "XXX");

    private static final Glyph F  = new Glyph(glyphSize, 'F',
            "XXX",
            "X  ",
            "XXX",
            "X  ",
            "X  ");

    private static final Glyph G = new Glyph(glyphSize, 'G',
            "XXX",
            "X  ",
            "X  ",
            "X X",
            "XXX");

    private static final Glyph H  = new Glyph(glyphSize, 'H',
            "X X",
            "X X",
            "XXX",
            "X X",
            "X X");

    private static final Glyph I = new Glyph(glyphSize, 'I',
            " X ",
            " X ",
            " X ",
            " X ",
            " X ");

    private static final Glyph J = new Glyph(glyphSize, 'J',
            " X ",
            " X ",
            " X ",
            " X ",
            "X  ");

    private static final Glyph K = new Glyph(glyphSize, 'K',
            "X X",
            "X X",
            "XX ",
            "X X",
            "X X");
    private static final Glyph L  = new Glyph(glyphSize, 'L',
            "X  ",
            "X  ",
            "X  ",
            "X  ",
            "XXX");

    private static final Glyph M = new Glyph(glyphSize, 'M',
            "XXX",
            "XXX",
            "XXX",
            "X X",
            "X X");

    private static final Glyph N = new Glyph(glyphSize, 'N',
            "XX ",
            "X X",
            "X X",
            "X X",
            "X X");

    private static final Glyph O = new Glyph(glyphSize, 'O',
            " X ",
            "X X",
            "X X",
            "X X",
            " X ");

    private static final Glyph P  = new Glyph(glyphSize, 'P',
            "XX ",
            "X X",
            "XX ",
            "X  ",
            "X  ");

    private static final Glyph Q = new Glyph(glyphSize, 'Q',
            " X "  ,
            "X X",
            "X X",
            "X X",
            " XX");

    private static final Glyph R  = new Glyph(glyphSize, 'R',
            "XX ",
            "X X",
            "XX ",
            "X X",
            "X X");

    private static final Glyph S = new Glyph(glyphSize, 'S',
            " XX",
            "X  ",
            " X ",
            "  X",
            "XX ");

    private static final Glyph T  = new Glyph(glyphSize, 'T',
            "XXX",
            " X ",
            " X ",
            " X ",
            " X ");

    private static final Glyph U = new Glyph(glyphSize, 'U',
            "X X",
            "X X",
            "X X",
            "X X",
            "XXX");

    private static final Glyph V  = new Glyph(glyphSize, 'V',
            "X X",
            "X X",
            "X X",
            " X ",
            " X ");

    private static final Glyph W = new Glyph(glyphSize, 'W',
            "X X",
            "X X",
            "XXX",
            "XXX",
            "XXX");

    private static final Glyph X  = new Glyph(glyphSize, 'X',
            "X X",
            "X X",
            " X ",
            "X X",
            "X X");

    private static final Glyph Y = new Glyph(glyphSize, 'Y',
            "X X",
            "X X",
            "XXX",
            " X ",
            " X ");

    private static final Glyph Z  = new Glyph(glyphSize, 'Z',
            "XXX",
            "  X",
            " X ",
            "X  ",
            "XXX");

    private static final Glyph EXCLAMATION_MARK  = new Glyph(glyphSize, '!',
            " X ",
            " X ",
            " X ",
            "   ",
            " X ");

    static private final Font font = new Font(Arrays.asList(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, ZERO, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, SPACE, EXCLAMATION_MARK));

    static final public Font font() {
        return font;
    }
}

package fr.craftinglabs.pi.apa102;


import java.util.Arrays;

public class TimeFont {
    private static final Size digitSize = new Size(3, 5);
    private static final Size symbolSize = new Size(1, 5);
    private static final Glyph ONE = new Glyph(digitSize, '1',
            " X ",
            " X ",
            " X ",
            " X ",
            " X ");
    private static final Glyph TWO = new Glyph(digitSize, '2',
            "XXX",
            "  X",
            "XXX",
            "X  ",
            "XXX");
    private static final Glyph THREE = new Glyph(digitSize, '3',
            "XXX",
            "  X",
            "XXX",
            "  X",
            "XXX");

    private static final Glyph FOUR = new Glyph(digitSize, '4',
            "X X",
            "X X",
            "XXX",
            "  X",
            "  X");
    private static final Glyph FIVE = new Glyph(digitSize, '5',
            "XXX",
            "X  ",
            "XXX",
            "  X",
            "XXX");

    private static final Glyph SIX = new Glyph(digitSize, '6',
            "XXX",
            "X  ",
            "XXX",
            "X X",
            "XXX");

    private static final Glyph SEVEN = new Glyph(digitSize, '7',
            "XXX",
            "  X",
            "  X",
            "  X",
            "  X");

    private static final Glyph EIGHT = new Glyph(digitSize, '8',
            "XXX",
            "X X",
            "XXX",
            "X X",
            "XXX");

    private static final Glyph NINE = new Glyph(digitSize, '9',
            "XXX",
            "X X",
            "XXX",
            "  X",
            "XXX");

    private static final Glyph ZERO = new Glyph(digitSize, '0',
            "XXX",
            "X X",
            "X X",
            "X X",
            "XXX");

    private static final Glyph COLUMN = new Glyph(symbolSize, ':',
            "  ",
            "X",
            " ",
            "X",
            " ");
    private static final Glyph SPACE = new Glyph(symbolSize, ' ',
            " ",
            " ",
            " ",
            " ",
            " ");

    static private final Font font = new Font(Arrays.asList(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, ZERO, SPACE, COLUMN));

    static final public Font font() {
        return font;
    }
}

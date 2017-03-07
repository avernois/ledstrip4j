package fr.craftinglabs.pi.apa102;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GlyphTest {
    @Test
    public void should_have_a_size() {
        Glyph glyph = new Glyph(new Size(4, 1), '.', "  X ");

        assertEquals(new Size(4, 1), glyph.size());
    }

    @Test
    public void should_represent_a_character() {
        Glyph glyph = new Glyph(new Size(4,4),'1',
                "  X ",
                "  X ",
                "  X ",
                "  X ");

        assertEquals('1', glyph.charForThatGlyph());
    }

    @Test
    public void should_have_correctly_interpret_given_pattern() {
        boolean[][] expected = new boolean[][]{
                {false, false, false, false, false},
                {false, false, false, false, false},
                {true, true, true, true, true},
                {false, false, false, false, false}};

        Glyph glyph = new Glyph(new Size(4,4), '1',
                "  X ",
                "  X ",
                "  X ",
                "  X ");

        for (int column = 0; column < 4; column++) {
            for (int line = 0; line < 4; line++) {
                assertEquals(expected[column][line], glyph.isSetAt(line, column));
            }
        }
    }
}
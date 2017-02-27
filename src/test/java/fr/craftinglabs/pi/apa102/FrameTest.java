package fr.craftinglabs.pi.apa102;

import org.junit.Test;

import static org.junit.Assert.*;

public class FrameTest {
    @Test public void
    should_have_size() {
        Frame frame = new Frame(new Size(2, 3));

        assertEquals(2, frame.getColumnNb());
        assertEquals(3, frame.getLineNb());
    }

    @Test public void
    should_be_full_of_black_cells_at_init() {
        Frame frame = new Frame(new Size(2, 3));

        for(int line = 0; line < 3; line++) {
            for (int column = 0; column < 2; column++) {
                assertEquals(ARGBColor.BLACK, frame.getPixelAt(line, column));
            }
        }
    }

    @Test public void
    should_set_color_at_the_correct_place() {
        ARGBColor color = new ARGBColor(10, 10, 10, 10);
        Frame frame = new Frame(new Size(2, 3));

        frame.setPixelAt(1, 1, color);

        assertEquals(color, frame.getPixelAt(1, 1));
    }
}
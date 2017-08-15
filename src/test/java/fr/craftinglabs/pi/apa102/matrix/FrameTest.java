package fr.craftinglabs.pi.apa102.matrix;

import fr.craftinglabs.pi.apa102.ARGBColor;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FrameTest {
    @Test public void
    should_have_size() {
        Size size = new Size(2, 3);
        Frame frame = new Frame(size);

        assertEquals(size, frame.getSize());
    }

    @Test public void
    should_be_full_of_black_cells_at_init() {
        Frame frame = new Frame(new Size(2, 3));

        for(int line = 0; line < 3; line++) {
            for (int column = 0; column < 2; column++) {
                Assert.assertEquals(ARGBColor.BLACK, frame.getPixelAt(column, line));
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
package fr.craftinglabs.pi.apa102;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class ColorBandTest {
    public static final ARGBColor BLUE = new ARGBColor(20, 0, 0, 255);

    @Test public void
    should_be_created_with_given_number_of_BLACK() {
        int nbColors = 0;

        ColorBand colors = new ColorBand(3);

        for(ARGBColor color : colors) {
            assertEquals(ARGBColor.BLACK, color);
            nbColors+=1;
        }

        assertEquals(3, nbColors);
    }

    @Test public void
    should_have_all_color_at_the_same_value() {
        ColorBand colors = new ColorBand(3);

        colors.setAllToSameColor(BLUE);

        for(ARGBColor color : colors) {
            assertEquals(BLUE, color);
        }
    }

    @Test public void
    should_set_color_at_given_place() {
        List<ARGBColor> expectedColors = new ArrayList<>(Collections.nCopies(3, ARGBColor.BLACK));
        expectedColors.set(0, BLUE);
        ColorBand colors = new ColorBand(3);

        colors.setColorAt(BLUE, 1);

        Iterator<ARGBColor> expectedIterator = expectedColors.iterator();
        for(ARGBColor color: colors) {
            ARGBColor expected = expectedIterator.next();
            assertEquals(expected, color);
        }
    }
}
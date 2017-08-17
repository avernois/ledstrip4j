package fr.craftinglabs.pi.apa102;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ARGBColorTest {

    @Test public void
    should_have_blue_component() {
        ARGBColor color = new ARGBColor(0, 0, 0, 120);

        assertEquals(120, color.getBlue());
    }

    @Test public void
    should_have_brightness_component() {
        ARGBColor color = new ARGBColor(20, 0, 0, 0);

        assertEquals(20, color.getBrightness());
    }

    @Test public void
    should_have_red_component() {
        ARGBColor color = new ARGBColor(0, 120, 0, 0);

        assertEquals(120, color.getRed());
    }

    @Test public void
    should_have_green_component() {
        ARGBColor color = new ARGBColor(0, 0, 120, 0);

        assertEquals(120, color.getGreen());
    }

    @Test(expected = IllegalArgumentException.class) public void
    should_throw_an_exception_when_brightness_is_above_31() {
        ARGBColor color = new ARGBColor(32, 0, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class) public void
    should_throw_an_exception_when_red_is_above_255() {
        ARGBColor color = new ARGBColor(0, 256, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class) public void
    should_throw_an_exception_when_green_is_above_255() {
        ARGBColor color = new ARGBColor(0, 0, 256, 0);
    }

    @Test(expected = IllegalArgumentException.class) public void
    should_throw_an_exception_when_blue_is_above_255() {
        ARGBColor color = new ARGBColor(0, 0, 0, 256);
    }
}
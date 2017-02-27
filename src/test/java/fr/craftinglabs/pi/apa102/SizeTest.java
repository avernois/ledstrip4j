package fr.craftinglabs.pi.apa102;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SizeTest {

    @Test public void
    should_have_a_number_of_column() {
        Size size = new Size(3, 4);
        assertEquals(3, size.nbColumns());
    }

    @Test public void
    should_have_a_number_of_line() {
        Size size = new Size(3, 4);
        assertEquals(4, size.nbLines());
    }
}

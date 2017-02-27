package fr.craftinglabs.pi.apa102;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Apa102LedStripTest {

    public static final int[] ZEROES = {0, 0, 0, 0, 0, 0, 0, 0};


    @Test public void
    should_update_the_strip_with_the_same_color() {
        RecordedLedStripIO io = new RecordedLedStripIO();
        LedStrip strip = new Apa102LedStrip(io);
        ColorBand colors = new ColorBand(3);
        colors.setAllToSameColor(new ARGBColor(1, 255,255,255));

        strip.setColors(colors);

        List<int[]> expected = Arrays.asList(
                ZEROES, ZEROES, ZEROES, ZEROES,
                new int[]{1, 1, 1, 0, 0, 0, 0, 1}, new int[]{1,1,1,1,1,1,1,1}, new int[]{1,1,1,1,1,1,1,1}, new int[]{1,1,1,1,1,1,1,1},
                new int[]{1, 1, 1, 0, 0, 0, 0, 1}, new int[]{1,1,1,1,1,1,1,1}, new int[]{1,1,1,1,1,1,1,1}, new int[]{1,1,1,1,1,1,1,1},
                new int[]{1, 1, 1, 0, 0, 0, 0, 1}, new int[]{1,1,1,1,1,1,1,1}, new int[]{1,1,1,1,1,1,1,1}, new int[]{1,1,1,1,1,1,1,1},
                ZEROES, ZEROES, ZEROES, ZEROES, ZEROES, ZEROES, ZEROES, ZEROES);

        for (int i = 0; i < expected.size(); i++) {
            assertArrayEquals(expected.get(i), io.getRecordedBits().get(i));
        }
    }
}

class RecordedLedStripIO implements LedStripIO {


    private final List<int[]> recordedBits = new ArrayList<>();

    @Override
    public void shift(int[] bits) {
        recordedBits.add(bits);
    }

    public List<int[]> getRecordedBits() {
        return recordedBits;
    }


}
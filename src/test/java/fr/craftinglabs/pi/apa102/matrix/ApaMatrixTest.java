package fr.craftinglabs.pi.apa102.matrix;

import fr.craftinglabs.pi.apa102.ARGBColor;
import fr.craftinglabs.pi.apa102.matrix.io.LedStrip;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApaMatrixTest {


    private FakeStrip fakeStrip;

    @Test public void
    should_set_the_number_of_led_corresponding_to_the_frame() {
        Frame frame = new Frame(new Size(4, 3));
        ApaMatrix matrix = new ApaMatrix(new Size(4, 3), fakeStrip);

        matrix.print(frame);

        assertThat(fakeStrip.getColors().size(), is(12));
    }

    @Test public void
    should_create_the_correct_set_of_colors_corresponding_the_frame() {
        ApaMatrix matrix = new ApaMatrix(new Size(4, 1), fakeStrip);
        Frame frame = new Frame(new Size(4, 1));
        List<ARGBColor> colors = addColorsToTheFrameOnLine(frame, 0);

        matrix.print(frame);

        assertThat(fakeStrip.getColors(), is(colors));
    }

    @Test public void
    should_represent_second_line_of_the_frame_in_reverse_order() {
        ApaMatrix matrix = new ApaMatrix(new Size(4, 2), fakeStrip);
        Frame frame = new Frame(new Size(4, 2));
        List<ARGBColor> addedColors = addColorsToTheFrameOnLine(frame, 1);
        Collections.reverse(addedColors);
        List<ARGBColor> expectedColors = new ArrayList<>(Collections.nCopies(4, ARGBColor.BLACK));
        expectedColors.addAll(addedColors);

        matrix.print(frame);

        assertThat(fakeStrip.getColors(), is(expectedColors));
    }

    @Before
    public void setUp() throws Exception {
        fakeStrip = new FakeStrip();
    }

    private List<ARGBColor> addColorsToTheFrameOnLine(Frame frame, int line) {
        List<ARGBColor> addedColors = new ArrayList<>();
        for (int column = 0; column < 4; column++) {
            ARGBColor color = new ARGBColor(10, column, 0, 0);
            addedColors.add(color);
            frame.setPixelAt(line, column, color);
        }
        return addedColors;
    }


}

class FakeStrip implements LedStrip {

    private List<ARGBColor> colors;

    public FakeStrip() {
        this.colors = new ArrayList<>();
    }

    @Override
    public void setColors(Iterable<ARGBColor> colorBand) {
        for (ARGBColor color : colorBand) {
            colors.add(color);
        }
    }

    public List<ARGBColor> getColors() {
        return colors;
    }
}
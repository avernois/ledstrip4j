package fr.craftinglabs.pi.apa102.text;

import fr.craftinglabs.pi.apa102.ARGBColor;
import fr.craftinglabs.pi.apa102.matrix.Frame;
import fr.craftinglabs.pi.apa102.matrix.Size;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class SlidingTextFramesTest {

    private static final ARGBColor BACKGROUND_COLOR = ARGBColor.BLACK;
    private static final ARGBColor FOREGROUND_COLOR = new ARGBColor(10, 10, 10, 10);

    @Test public void
    should_have_a_first_frame_filled_with_backgroundcolor() {
        Size frameSize = new Size(5, 5);
        SlidingTextFrames frames = new SlidingTextFrames("a", frameSize, FOREGROUND_COLOR, BACKGROUND_COLOR, TextFont.font());

        Frame frame = frames.iterator().next();

        for(int column=0; column < frameSize.nbColumns(); column++) {
            for(int line = 0; line < frameSize.nbLines(); line++) {
                assertEquals(BACKGROUND_COLOR, frame.getPixelAt(line, column));
            }
        }
    }

    @Test public void
    should_have_last_frame_filled_with_backgroundcolor() {
        Size frameSize = new Size(5, 5);

        SlidingTextFrames frames = new SlidingTextFrames("a", frameSize, FOREGROUND_COLOR, BACKGROUND_COLOR, TextFont.font());

        Frame lastFrame = getLastFrame(frames);
        for(int column=0; column < frameSize.nbColumns(); column++) {
            for(int line = 0; line < frameSize.nbLines(); line++) {
                assertEquals(BACKGROUND_COLOR, lastFrame.getPixelAt(line, column));
            }
        }
    }

    @Test public void
    should_have_first_column_of_text_on_last_column_of_frame_on_second_frame() {
        Size frameSize = new Size(5, 5);

        SlidingTextFrames frames = new SlidingTextFrames("a", frameSize, FOREGROUND_COLOR, BACKGROUND_COLOR, TextFont.font());

        Frame secondFrame = getFrameNumber(2, frames);

        List<ARGBColor> lastColumnColors = Arrays.asList(FOREGROUND_COLOR, FOREGROUND_COLOR, FOREGROUND_COLOR, FOREGROUND_COLOR, FOREGROUND_COLOR);
        assertColumnNumberMatch(secondFrame.getColumnNb() - 1, secondFrame, lastColumnColors);
    }

    @Test public void
    should_have_two_first_columns_of_text_on_lasts_column_of_frame_on_third_frame() {
        Size frameSize = new Size(5, 5);

        SlidingTextFrames frames = new SlidingTextFrames("a", frameSize, FOREGROUND_COLOR, BACKGROUND_COLOR, TextFont.font());

        Frame thirdFrame = getFrameNumber(3, frames);

        List<ARGBColor> beforeLastColumnColors = Arrays.asList(FOREGROUND_COLOR, FOREGROUND_COLOR, FOREGROUND_COLOR, FOREGROUND_COLOR, FOREGROUND_COLOR);
        assertColumnNumberMatch(thirdFrame.getColumnNb() - 2, thirdFrame, beforeLastColumnColors);

        List<ARGBColor> lastColumnsColors = Arrays.asList(BACKGROUND_COLOR, FOREGROUND_COLOR, BACKGROUND_COLOR, BACKGROUND_COLOR, FOREGROUND_COLOR);
        assertColumnNumberMatch(thirdFrame.getColumnNb() - 1, thirdFrame, lastColumnsColors);
    }

    @Test public void
    should_return_frame_of_the_given_frame_size() {
        Size frameSize = new Size(5, 5);

        SlidingTextFrames frames = new SlidingTextFrames("a", frameSize, FOREGROUND_COLOR, BACKGROUND_COLOR, TextFont.font());

        for(Frame frame : frames) {
            assertSameSize(frameSize, frame);
        }
    }

    @Test public void
    should_return_a_new_different_iterator_at_each_call_() {
        Size frameSize = new Size(5, 5);
        SlidingTextFrames frames = new SlidingTextFrames("a", frameSize, FOREGROUND_COLOR, BACKGROUND_COLOR, TextFont.font());

        Iterator<Frame> firstIterator = frames.iterator();
        Iterator<Frame> secondIterator = frames.iterator();

        assertNotSame(firstIterator, secondIterator);
    }


    private void assertSameSize(Size frameSize, Frame frame) {
        assertEquals(frameSize.nbColumns(), frame.getColumnNb());
        assertEquals(frameSize.nbLines(), frame.getLineNb());
    }

    private Frame getLastFrame(SlidingTextFrames frames) {
        Iterator<Frame> iterator = frames.iterator();
        Frame lastFrame = null;
        do {
            lastFrame = iterator.next();
        } while (iterator.hasNext());

        return lastFrame;
    }

    private void assertColumnNumberMatch(int columnNumber, Frame secondFrame, List<ARGBColor> lastColumnColors) {
        for(int line = 0; line < secondFrame.getLineNb(); line++) {
            assertEquals(lastColumnColors.get(line), secondFrame.getPixelAt(line, columnNumber));
        }
    }

    private Frame getFrameNumber(int frameNb, SlidingTextFrames frames) {
        Iterator<Frame> iterator = frames.iterator();
        for(int frameIndex = 0; frameIndex < frameNb - 1; frameIndex ++) {
            iterator.next();
        }

        return iterator.next();
    }
}
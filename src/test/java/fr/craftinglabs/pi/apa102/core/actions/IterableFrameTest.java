package fr.craftinglabs.pi.apa102.core.actions;

import fr.craftinglabs.pi.apa102.ARGBColor;
import fr.craftinglabs.pi.apa102.matrix.Frame;
import fr.craftinglabs.pi.apa102.matrix.Size;
import org.junit.Test;

import static org.junit.Assert.*;

public class IterableFrameTest {

    @Test public void
    should_have_the_frame_given_at_constuction_time() {
        Frame aFrame = new Frame(new Size(4,4), new ARGBColor(10,10,10,10));
        IterableFrame iterableFrame = new IterableFrame(aFrame);

        for(Frame fram : iterableFrame) {
            assertEquals(aFrame, fram);
        }
    }

    @Test public void
    should_have_only_one_frame() {
        Frame aFrame = new Frame(new Size(4,4), new ARGBColor(10,10,10,10));
        IterableFrame iterableFrame = new IterableFrame(aFrame);

        int frameCount = 0;
        for(Frame frame : iterableFrame) {
            frameCount += 1;
        }

        assertEquals(1, frameCount);
    }

}
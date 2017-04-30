package fr.craftinglabs.pi.apa102.text;

import fr.craftinglabs.pi.apa102.ARGBColor;
import fr.craftinglabs.pi.apa102.font.Font;
import fr.craftinglabs.pi.apa102.font.Glyph;
import fr.craftinglabs.pi.apa102.matrix.Size;

import java.util.ArrayList;
import java.util.List;

public class SlidingTextFramesBuilder {

    private Size frameSize;
    private ARGBColor backgroundColor = ARGBColor.BLACK;
    private ARGBColor color = new ARGBColor(10, 255, 255, 255);
    private Font font = TextFont.font();
    private String text;

    public SlidingTextFramesBuilder sized(Size size) {
        this.frameSize = size;
        return this;
    }

    public SlidingTextFramesBuilder withBackgroundColor(ARGBColor backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public SlidingTextFramesBuilder withFontColor(ARGBColor color) {
        this.color = color;
        return this;
    }

    public SlidingTextFramesBuilder forText(String text) {
        this.text = text;

        return this;
    }

    public SlidingTextFrames build() {
        return new SlidingTextFrames(text, frameSize, color, backgroundColor, font);
    }

    public static SlidingTextFramesBuilder slidingTextFrames() {
        return new SlidingTextFramesBuilder();
    }
}
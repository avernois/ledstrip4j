package fr.craftinglabs.pi.apa102.time;


import fr.craftinglabs.pi.apa102.ARGBColor;
import fr.craftinglabs.pi.apa102.matrix.Frame;
import fr.craftinglabs.pi.apa102.matrix.Size;
import fr.craftinglabs.pi.apa102.font.Font;
import fr.craftinglabs.pi.apa102.font.Glyph;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeFrameBuilder {

    public static final int NB_COLUMN_FOR_TIME_TEXT = 4 * 4 + 2;
    public static final int NB_LINE_FOR_TIME_TEXT = 5;
    private Font font = TimeFont.font();
    private ARGBColor backgroundColor;
    private ARGBColor color;
    private Size size;

    public static TimeFrameBuilder aTimeFrame() {
        return new TimeFrameBuilder();
    }

    public TimeFrameBuilder sized(Size size) {
        this.size = size;
        return this;
    }

    public TimeFrameBuilder withBackgroundColor(ARGBColor backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public TimeFrameBuilder withFontColor(ARGBColor color) {
        this.color = color;
        return this;
    }

    public Frame buildForTime(LocalTime time) {
        String timeString = getTimeAsString(time);

        int columnOffset = (size.nbColumns() - NB_COLUMN_FOR_TIME_TEXT)/2;
        int lineOffset = (size.nbLines() - NB_LINE_FOR_TIME_TEXT) / 2;

        Frame frame = new Frame(size, backgroundColor);

        for (int charIndex = 0; charIndex < timeString.length(); charIndex++) {
            Glyph glyph = font.glyphFor(timeString.charAt(charIndex));
            addGlyph(glyph, frame, columnOffset, lineOffset, color);
            columnOffset += glyph.size().nbColumns() + 1 ;
        }

        return frame;
    }


    private static String getTimeAsString(LocalTime time) {
        String sep = time.getSecond() % 2 == 0 ? ":": " ";
        return time.format(DateTimeFormatter.ofPattern("HH" + sep + "mm"));
    }

    private static void addGlyph(Glyph one, Frame frame1, int columnOffset, int lineOffset, ARGBColor color) {
        Size size = one.size();
        for(int lineIndex = 0; lineIndex < size.nbLines(); lineIndex ++) {
            for (int columnIndex = 0; columnIndex < size.nbColumns() ; columnIndex++) {
                if(one.isSetAt(lineIndex,columnIndex)) {
                    frame1.setPixelAt(4 - lineIndex + lineOffset, columnIndex + columnOffset, color);
                }
            }
        }
    }
}

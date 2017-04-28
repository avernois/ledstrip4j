package fr.craftinglabs.pi.apa102;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeFrameBuilder {

    private Font font;
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

    public TimeFrameBuilder withFont(Font font) {
        this.font = font;
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

        int columnOffset = (size.nbColumns() - 18)/2;
        int lineOffset = (size.nbLines() - 5) / 2;

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

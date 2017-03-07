package fr.craftinglabs.pi.apa102;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeFrameBuilder {

    public static Frame time(LocalTime time) {
        String sep = time.getSecond() % 2 == 0 ? ":": " ";
        String timeString = time.format(DateTimeFormatter.ofPattern("HH" + sep + "mm"));

        int columnOffset = 1;
        int lineOffset = 1;

        Frame frame = new Frame(new Size(20, 7));
        for (int charIndex = 0; charIndex < timeString.length(); charIndex++) {
            Glyph glyph = TimeFont.font().glyphFor(timeString.charAt(charIndex));
            addGlyph(glyph, frame, columnOffset, lineOffset);
            columnOffset += glyph.size().nbColumns() + 1 ;
        }

        return frame;
    }

    private static void addGlyph(Glyph one, Frame frame1, int columnOffset, int lineOffset) {
        ARGBColor color = new ARGBColor(5, 200, 200, 0) ;
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

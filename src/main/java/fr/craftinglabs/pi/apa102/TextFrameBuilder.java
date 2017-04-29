package fr.craftinglabs.pi.apa102;

import fr.craftinglabs.pi.apa102.font.Font;
import fr.craftinglabs.pi.apa102.font.Glyph;
import fr.craftinglabs.pi.apa102.matrix.Frame;
import fr.craftinglabs.pi.apa102.matrix.Size;

import java.util.ArrayList;
import java.util.List;

public class TextFrameBuilder {

    private Size size;
    private ARGBColor backgroundColor;
    private ARGBColor color;
    private Font font = TextFont.font();

    private List<Glyph> glyphs = new ArrayList<>();

    public TextFrameBuilder sized(Size size) {
        this.size = size;
        return this;
    }

    public TextFrameBuilder withBackgroundColor(ARGBColor backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public TextFrameBuilder withFontColor(ARGBColor color) {
        this.color = color;
        return this;
    }

    public Frame buildText(String text, int offset) {

        int columns = 0;

        for (int charIndex = 0; charIndex < text.length(); charIndex++) {
            Glyph glyph = font.glyphFor(text.charAt(charIndex));
            glyphs.add(glyph);
            columns += glyph.size().nbColumns() + 1;
        }

        Landscape full = new Landscape(glyphs, new Size(columns, size.nbLines()), color, backgroundColor);
        Frame frame = full.frameStartingColumn(offset, size);

        return frame;
    }

    public static TextFrameBuilder aTimeFrame() {
        return new TextFrameBuilder();
    }

}

class Landscape {
    private ARGBColor[][] fullFrame; // boolean would be better
    private Size size;
    private final ARGBColor color;
    private final ARGBColor backgroundColor;
    private List<Glyph> glyphs;

    public Landscape(List<Glyph> glyphs, Size size, ARGBColor color, ARGBColor backgroundColor) {
        this.glyphs = glyphs;
        this.size = size;
        this.color = color;
        this.backgroundColor = backgroundColor;

        fullFrame = new ARGBColor[size.nbColumns()][size.nbLines()];
        int columnOffset = 0;
        int lineOffset = 0;
        for (Glyph glyph : glyphs) {
            addGlyph(glyph, columnOffset, lineOffset);
            columnOffset = columnOffset + glyph.size().nbColumns() + 1;
        }
    }

    private void addGlyph(Glyph one, int columnOffset, int lineOffset) {
        Size size = one.size();
        for (int lineIndex = 0; lineIndex < size.nbLines(); lineIndex++) {
            for (int columnIndex = 0; columnIndex < size.nbColumns(); columnIndex++) {
                if (one.isSetAt(lineIndex, columnIndex)) {
                    fullFrame[columnIndex + columnOffset][4 - lineIndex + lineOffset] = color;
                } else {
                    fullFrame[columnIndex + columnOffset][4 - lineIndex + lineOffset] = backgroundColor;
                }
            }
        }

    }

    public Frame frameStartingColumn(int startingOffset, Size size) {
        Frame frame = new Frame(size, backgroundColor);
        for (int lines = 0; lines < size.nbLines(); lines++) {
            for (int columns = 0; columns < size.nbColumns(); columns++) {

                ARGBColor color = fullFrame[columns + startingOffset][lines];
                if(color == null) {
                    frame.setPixelAt(lines, columns, backgroundColor);
                } else {
                    frame.setPixelAt(lines, columns, color);
                }
            }
        }
        return frame;
    }
}
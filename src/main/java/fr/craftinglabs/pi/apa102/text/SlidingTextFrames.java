package fr.craftinglabs.pi.apa102.text;

import fr.craftinglabs.pi.apa102.ARGBColor;
import fr.craftinglabs.pi.apa102.font.Font;
import fr.craftinglabs.pi.apa102.font.Glyph;
import fr.craftinglabs.pi.apa102.matrix.Frame;
import fr.craftinglabs.pi.apa102.matrix.Size;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SlidingTextFrames implements Iterable<Frame> {
    private final TextLandscape landscape;
    private final Size frameSize;

    public SlidingTextFrames(String text, Size frameSize, ARGBColor color, ARGBColor backgroundColor, Font font) {
        List<Glyph> glyphs = new ArrayList<>();

        for (int charIndex = 0; charIndex < text.length(); charIndex++) {
            Glyph glyph = font.glyphFor(Character.toUpperCase(text.charAt(charIndex)));
            glyphs.add(glyph);
        }

        this.frameSize = frameSize;
        this.landscape = new TextLandscape(glyphs, frameSize, color, backgroundColor);
    }

    @Override
    public Iterator<Frame> iterator() {
        return new SlidingTextIterator();
    }

    class SlidingTextIterator implements Iterator<Frame> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < landscape.getLandscapeSize().nbColumns() - frameSize.nbColumns();
        }

        @Override
        public Frame next() {
            return landscape.frameStartingColumn(current++, frameSize);
        }
    }
}


class TextLandscape {

    private boolean[][] fullFrame;
    private Size landscapeSize;
    private final ARGBColor color;
    private final ARGBColor backgroundColor;

    public TextLandscape(List<Glyph> glyphs, Size frameSize, ARGBColor color, ARGBColor backgroundColor) {
        int nbColumns = glyphs.stream().reduce(0, (size, glyph) -> size += glyph.size().nbColumns() + 1, (size1, size2) -> size1 + size2);

        this.landscapeSize = new Size(nbColumns + 2*frameSize.nbColumns(), frameSize.nbLines());
        this.color = color;
        this.backgroundColor = backgroundColor;

        fullFrame = new boolean[landscapeSize.nbColumns()][landscapeSize.nbLines()];
        int columnOffset = frameSize.nbColumns();
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
                fullFrame[columnIndex + columnOffset][4 - lineIndex + lineOffset] = one.isSetAt(lineIndex, columnIndex);
            }
        }
    }

    public Frame frameStartingColumn(int startingOffset, Size size) {
        Frame frame = new Frame(size, backgroundColor);
        for (int lines = 0; lines < size.nbLines(); lines++) {
            for (int columns = 0; columns < size.nbColumns(); columns++) {
                Boolean ledState = fullFrame[columns + startingOffset][lines];
                if(ledState) {
                    frame.setPixelAt(lines, columns, color);
                } else {
                    frame.setPixelAt(lines, columns, backgroundColor);
                }
            }
        }
        return frame;
    }


    public Size getLandscapeSize() {
        return landscapeSize;
    }
}

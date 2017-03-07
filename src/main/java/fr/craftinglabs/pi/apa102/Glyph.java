package fr.craftinglabs.pi.apa102;

public class Glyph {
    private Size size;
    private final Character character;
    private final boolean[][] glyph;

    public Glyph(Size size, Character character, String... lines) {
        this.glyph = new boolean[size.nbColumns()][size.nbLines()];
        this.size = size;
        this.character = character;

        for (int lineIndex = 0; lineIndex < lines.length; lineIndex ++) {
            String line = lines[lineIndex];
            for (int columnIndex = 0; columnIndex < line.length(); columnIndex++) {
                if(line.charAt(columnIndex) == 'X') {
                    glyph[columnIndex][lineIndex] = true;
                }
            }
        }
    }

    public Size size() {
        return size;
    }

    public char charForThatGlyph() {
        return character;
    }

    public boolean isSetAt(int lineIndex, int columnIndex) {
        return glyph[columnIndex][lineIndex];
    }
}

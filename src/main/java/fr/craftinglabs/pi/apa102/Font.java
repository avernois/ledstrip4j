package fr.craftinglabs.pi.apa102;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Font {
    private final Map<Character, Glyph> glyphs = new HashMap<>();

    public Font(List<Glyph> glyphs) {
        for(Glyph glyph: glyphs)
            this.glyphs.put(glyph.charForThatGlyph(), glyph);
    }

    public Glyph glyphFor(Character character) {
        return glyphs.get(character);
    }
}

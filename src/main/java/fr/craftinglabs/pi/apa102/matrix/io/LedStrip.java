package fr.craftinglabs.pi.apa102.matrix.io;

import fr.craftinglabs.pi.apa102.ARGBColor;

public interface LedStrip {
    void setColors(Iterable<ARGBColor> colorBand);
}

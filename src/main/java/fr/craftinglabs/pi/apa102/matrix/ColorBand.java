package fr.craftinglabs.pi.apa102.matrix;

import fr.craftinglabs.pi.apa102.ARGBColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ColorBand implements  Iterable<ARGBColor> {
    private int nbLeds;
    private List<ARGBColor> colors = new ArrayList<>();


    public ColorBand(int nbLeds) {
        this.nbLeds = nbLeds;
        colors.addAll(Collections.nCopies(nbLeds, ARGBColor.BLACK));
    }

    public void setAllToSameColor(ARGBColor color) {
        colors.clear();
        for(int i = 0; i < nbLeds; i++) {
            colors.add(color);
        }
    }

    public void setColorAt(ARGBColor color, int position) {
        colors.set(position -1, color);
    }

    @Override
    public Iterator<ARGBColor> iterator() {
        return colors.iterator();
    }
}

package fr.craftinglabs.pi.apa102.matrix;

import fr.craftinglabs.pi.apa102.ARGBColor;

public class Frame {
    private ARGBColor frame[][];
    private Size size;

    public Frame(Size size) {
        this(size, ARGBColor.BLACK);
    }

    public Frame(Size size, ARGBColor initColor) {
        this.size = size;
        frame = new ARGBColor[size.nbColumns()][size.nbLines()];
        initFrame(initColor);
    }

    public ARGBColor getPixelAt(int column, int line) {
        return frame[column][line];
    }

    public void setPixelAt(int column, int line, ARGBColor color) {
        frame[column][line] = color;
    }

    private void initFrame(ARGBColor initColor) {
        for (int columnIndex = 0; columnIndex < size.nbColumns(); columnIndex++) {
            for (int lineIndex= 0; lineIndex < size.nbLines(); lineIndex++) {
                frame[columnIndex][lineIndex] = initColor;
            }
        }
    }

    public int getColumnNb() {
        return size.nbColumns();
    }

    public int getLineNb() {
        return size.nbLines();
    }
}

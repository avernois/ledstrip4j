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
        frame = new ARGBColor[size.nbLines()][size.nbColumns()];
        initFrame(initColor);
    }

    public ARGBColor getPixelAt(int line, int column) {
        return frame[line][column];
    }

    public void setPixelAt(int line, int column, ARGBColor color) {
        frame[line][column] = color;
    }

    private void initFrame(ARGBColor initColor) {
        for (int columnIndex = 0; columnIndex < size.nbColumns(); columnIndex++) {
            for (int lineIndex= 0; lineIndex < size.nbLines(); lineIndex++) {
                frame[lineIndex][columnIndex] = initColor;
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

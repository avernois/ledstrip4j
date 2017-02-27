package fr.craftinglabs.pi.apa102;

public class Size {
    private final int nbColumn;
    private final int nbLines;

    public Size(int nbColumn, int nbLines) {
        this.nbColumn = nbColumn;
        this.nbLines = nbLines;
    }

    public int nbColumns() {
        return nbColumn;
    }

    public int nbLines() {
        return nbLines;
    }
}

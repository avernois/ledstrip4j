package fr.craftinglabs.pi.apa102.matrix.io;

import fr.craftinglabs.pi.apa102.ARGBColor;
import fr.craftinglabs.pi.apa102.matrix.Size;

public class ConsoleTwoColorMatrixLedStrip implements LedStrip {

    private Size size;
    private Boolean[][] frame;
    private ARGBColor background;

    public ConsoleTwoColorMatrixLedStrip(Size size, ARGBColor background) {
        this.size = size;
        this.frame = new Boolean[size.nbColumns()][size.nbLines()];
        this.background = background;
    }

    @Override
    public void setColors(Iterable<ARGBColor> colorBand) {
        int column = 0;
        int line = 0;
        int direction = 1;
        for(ARGBColor color: colorBand) {
            if(column >= size.nbColumns()) {
                column = column -1;
                line ++;
                direction = -1;
            }
            if (column < 0) {
                column = 0;
                line ++;
                direction = 1;
            }
            frame[column][line] = background.equals(color);
            column += direction;
        }

        System.out.println("printing: ");
        for (int lineNb = size.nbLines() -1 ; lineNb >=0; lineNb--) {
            for (int columNb = 0; columNb < size.nbColumns(); columNb++) {
                System.out.print(frame[columNb][lineNb]?" ":"X");
            }
            System.out.println();
        }
    }
}

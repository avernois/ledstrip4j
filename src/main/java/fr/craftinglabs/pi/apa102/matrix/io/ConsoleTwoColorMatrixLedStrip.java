package fr.craftinglabs.pi.apa102.matrix.io;

import fr.craftinglabs.pi.apa102.ARGBColor;
import fr.craftinglabs.pi.apa102.matrix.Size;

public class ConsoleTwoColorMatrixLedStrip implements LedStrip {

    private Size size;
    private ARGBColor background;

    public ConsoleTwoColorMatrixLedStrip(Size size, ARGBColor background) {
        this.size = size;
        this.background = background;
    }

    @Override
    public void setColors(Iterable<ARGBColor> colorBand) {
        boolean[][] frame = convertColorBandToMatrix(colorBand, size)   ;
        printFrameOnConsole(frame);
    }

    private boolean[][] convertColorBandToMatrix(Iterable<ARGBColor> colorBand, Size size) {
        boolean[][] frame = new boolean[size.nbColumns()][size.nbLines()];
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

        return frame;
    }

    private void printFrameOnConsole(boolean[][] frame) {
        clearConsole();
        for (int lineNb = size.nbLines() -1 ; lineNb >=0; lineNb--) {
            for (int columNb = 0; columNb < size.nbColumns(); columNb++) {
                System.out.print(frame[columNb][lineNb]?" ":"X");
            }
            System.out.println();
        }
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

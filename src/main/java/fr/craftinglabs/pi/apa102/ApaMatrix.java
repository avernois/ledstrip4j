package fr.craftinglabs.pi.apa102;

public class ApaMatrix {

    private Size size;
    private LedStrip strip;

    public ApaMatrix(Size size, LedStrip strip) {
        this.size = size;
        this.strip = strip;
    }

    public void print(Frame frame){
        strip.setColors(frameToColorBand(frame));
    }


    private static ColorBand frameToColorBand(Frame frame) {
        int lineNb = frame.getLineNb();
        int columnNb = frame.getColumnNb();
        ColorBand colors = new ColorBand(lineNb * columnNb);

        for (int lineIndex = 0; lineIndex < lineNb; lineIndex++) {
            if(lineIndex % 2 == 0) {
                for (int columnIndex = 0; columnIndex < columnNb; columnIndex++) {
                    colors.setColorAt(frame.getPixelAt(lineIndex, columnIndex), lineIndex * columnNb + columnIndex +1);
                }
            } else {
                for (int columnIndex = 0; columnIndex < columnNb; columnIndex++) {
                    colors.setColorAt(frame.getPixelAt(lineIndex, columnIndex), lineIndex * columnNb + columnNb - columnIndex);
                }
            }
        }

        return colors;
    }

}

package fr.craftinglabs.pi.apa102;

public class MonochromeLedStrip {
    private static final int[] ZEROES = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    private static final int[] ONES = new int[]{1, 1, 1, 1, 1, 1, 1, 1};

    private LedStripIO io;
    int nbLeds;

    public MonochromeLedStrip(LedStripIO io, int nbLeds) {
        this.io = io;
        this.nbLeds = nbLeds;
    }

    public void clear() {
        changeColor(ARGBColor.BLACK);
    }

    public void changeColor(ARGBColor color) {
        for (int i = 0; i < 4; i++) {
            io.shift(ZEROES);
        }

        for (int i = 0; i < nbLeds; i++) {
            io.shift(toBits(color.getBrightness() + 128 + 64 + 32));
            io.shift(toBits(color.getBlue()));
            io.shift(toBits(color.getGreen()));
            io.shift(toBits(color.getRed()));
        }

        for (int i = 0; i < 16; i++) {
            io.shift(ZEROES);
        }
    }

    private int[] toBits(int byt) {
        int bytes[] = new int[8];

        int rest = byt;

        for (int i = 7; i >= 0; i--) {
            double pow = Math.pow(2, i);
            if (rest >= pow) {
                bytes[7 - i] = 1;
                rest -= pow;
            } else {
                bytes[7 - i] = 0;
            }
        }

        return bytes;
    }
}

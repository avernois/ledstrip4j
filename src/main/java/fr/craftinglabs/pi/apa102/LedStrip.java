package fr.craftinglabs.pi.apa102;

public class LedStrip {

    private static final int[] ZEROES = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    private LedStripIO io;

    public LedStrip(LedStripIO io) {
        this.io = io;
    }

    public void setColors(Iterable<ARGBColor> colorBand) {

        sendBeginFrame();

        for (ARGBColor color: colorBand) {
            sendColor(color);
        }

        sendEndFrame();
    }

    private void sendColor(ARGBColor color) {
        io.shift(toBits(color.getBrightness() + 128 + 64 + 32));
        io.shift(toBits(color.getBlue()));
        io.shift(toBits(color.getGreen()));
        io.shift(toBits(color.getRed()));
    }

    private void sendBeginFrame() {
        for (int i = 0; i < 4; i++) {
            io.shift(ZEROES);
        }
    }

    private void sendEndFrame() {
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

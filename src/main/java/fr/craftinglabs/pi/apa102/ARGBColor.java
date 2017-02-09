package fr.craftinglabs.pi.apa102;

public class ARGBColor {
    public static final ARGBColor BLACK = new ARGBColor(0, 0, 0, 0);
    private int red;
    private int green;
    private int blue;
    private int brightness;

    public ARGBColor(int brightness, int red, int green, int blue) {
        if (isUnder(brightness, 31)
                || isUnder(red, 255)
                || isUnder(green, 255)
                || isUnder(blue, 255)) {
            throw new IllegalArgumentException();
        }

        this.brightness = brightness;

        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    private boolean isUnder(int brightness, int maxValue) {
        return brightness > maxValue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getBrightness() {
        return brightness;
    }
}

package fr.craftinglabs.pi.apa102;

import com.pi4j.io.gpio.*;


public class Apa {

    public static final int[] ZEROES = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    public static final int[] ONES = new int[]{1, 1, 1, 1, 1, 1, 1, 1};

    public static final int[][] BLUE = new int[][]{
            new int[]{1, 1, 1, 0, 0, 0, 1, 1},
            new int[]{0, 1, 0, 1, 1, 1, 1, 1},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0}};

    public static final int[][] GREEN = new int[][]{
            new int[]{1, 1, 1, 0, 0, 0, 1, 1},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 1, 1, 1, 1, 1, 1},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0}};

    public static final int[][] RED = new int[][]{
            new int[]{1, 1, 1, 0, 0, 0, 1, 1},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 1, 1, 1, 1, 1, 1}};

    public static final int[][] OTHER = new int[][]{
            new int[]{1, 1, 1, 0, 0, 0, 1, 1},
            new int[]{0, 0, 0, 0, 1, 1, 1, 1},
            new int[]{0, 0, 0, 0, 0, 0, 1, 0},
            new int[]{0, 1, 1, 1, 1, 1, 1, 1}};

    public static final int[][] BLACK = new int[][]{
            new int[]{1, 1, 1, 0, 0, 0, 0, 0},
            ZEROES,
            ZEROES,
            ZEROES
    };

    public static void main(String[] args) throws InterruptedException {
        final GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput clock = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "CLOCK", PinState.LOW);
        GpioPinDigitalOutput data = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "DATA", PinState.LOW);


        int nbLeds = 120;

        send_color(clock, data, BLACK, nbLeds);

        System.out.println("OFF");
        Thread.sleep(2000);

        send_color(clock, data, RED, nbLeds);

        System.out.println("RED");
        Thread.sleep(2000);

        send_color(clock, data, BLUE, nbLeds);

        System.out.println("BLUE");
        Thread.sleep(2000);


        send_color(clock, data, GREEN, nbLeds);

        System.out.println("GREEN");
        Thread.sleep(2000);

        send_color(clock, data, OTHER, nbLeds);

        System.out.println("OTHER");
        Thread.sleep(2000);


        send_color(clock, data, BLACK, nbLeds);

        System.out.println("OFF");
        Thread.sleep(2000);

        send_color(clock, data, RED, nbLeds);

        System.out.println("RED");
        Thread.sleep(2000);

        send_color(clock, data, BLUE, nbLeds);

        System.out.println("BLUE");
        Thread.sleep(2000);


        send_color(clock, data, GREEN, nbLeds/2);

        System.out.println("GREEN");
        Thread.sleep(2000);

        send_color(clock, data, OTHER, nbLeds/2);
        System.out.println("OTHER");
        Thread.sleep(2000);

    }




    private static void send_color(GpioPinDigitalOutput clock, GpioPinDigitalOutput data, int[][] color, int nb) {

        for (int i = 0; i < 4; i++) {
            shift(clock, data, ZEROES);
        }

        for (int i = 0; i < nb; i++) {
            for (int[] colorComposant : color) {
                shift(clock, data, colorComposant);
            }
        }
        for (int i = 0; i < 16; i++) {
            shift(clock, data, ZEROES);
        }
    }

    private static void shift(GpioPinDigitalOutput clock, GpioPinDigitalOutput data, int[] colorComposant) {
        //Shift.shiftOut((byte) data.getPin().getAddress(), (byte) clock.getPin().getAddress(), (byte) Shift.LSBFIRST, toByte(colorComposant));

        for(int i:colorComposant) {
            if(i>0) {
                data.high();

            } else {
                data.low();

            }
            clock.high();
            clock.low();
        }
    }
}

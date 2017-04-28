package fr.craftinglabs.pi.apa102.matrix.io;

import com.pi4j.io.gpio.GpioPinDigitalOutput;


public class LedStripGPIO implements LedStripIO {

    private final GpioPinDigitalOutput data;
    private final GpioPinDigitalOutput clock;

    public LedStripGPIO(GpioPinDigitalOutput data, GpioPinDigitalOutput clock) {
        this.data = data;
        this.clock = clock;
    }

    @Override
    public void shift(int[] bits) {
        for (int i : bits) {
            if (i > 0) {
                data.high();
            } else {
                data.low();
            }
            clock.high();
            clock.low();
        }
    }
}

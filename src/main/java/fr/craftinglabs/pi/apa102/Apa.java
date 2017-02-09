package fr.craftinglabs.pi.apa102;

import com.pi4j.io.gpio.*;


public class Apa {

    public static void main(String[] args) throws InterruptedException {
        final GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput clock = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "CLOCK", PinState.LOW);
        GpioPinDigitalOutput data = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "DATA", PinState.LOW);

        LedStripIO io = new LedStripGPIO(data, clock);
        MonochromeLedStrip strip = new MonochromeLedStrip(io, 120);

        int waitingTime = 500;
        strip.clear();

        strip.changeColor(new ARGBColor(3, 50, 0, 0));
        Thread.sleep(waitingTime);

        strip.changeColor(new ARGBColor(3, 200, 0, 100));
        Thread.sleep(waitingTime);

        strip.changeColor(new ARGBColor(20, 0, 127, 0));
        Thread.sleep(waitingTime);

        strip.changeColor(new ARGBColor(10, 127, 2, 15));
        Thread.sleep(waitingTime);

        strip.clear();
    }
}


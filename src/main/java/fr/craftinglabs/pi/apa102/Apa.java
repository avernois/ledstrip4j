package fr.craftinglabs.pi.apa102;

import com.pi4j.io.gpio.*;


public class Apa {

    public static void main(String[] args) throws InterruptedException {
        final GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput clock = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "CLOCK", PinState.LOW);
        GpioPinDigitalOutput data = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "DATA", PinState.LOW);

        LedStripIO io = new LedStripGPIO(data, clock);

        int waitingTime = 1000;

        LedStrip ledStrip = new LedStrip(io);
        ColorBand colorBand = new ColorBand(120);

        colorBand.setAllToSameColor(new ARGBColor(3, 50, 0, 0));
        ledStrip.setColors(colorBand);
        Thread.sleep(waitingTime);

        colorBand.setAllToSameColor(new ARGBColor(3, 200, 0, 100));
        ledStrip.setColors(colorBand);
        Thread.sleep(waitingTime);

        colorBand.setAllToSameColor(new ARGBColor(20, 0, 127, 0));
        ledStrip.setColors(colorBand);
        Thread.sleep(waitingTime);

        colorBand.setAllToSameColor(new ARGBColor(2, 127, 2, 15));
        ledStrip.setColors(colorBand);

        ARGBColor color1 = new ARGBColor(10, 2, 127, 15);
        colorBand.setColorAt(color1, 24);
        colorBand.setColorAt(color1, 25);
        colorBand.setColorAt(color1, 72);
        colorBand.setColorAt(color1, 73);
        colorBand.setColorAt(color1, 120);
        ARGBColor color2 = new ARGBColor(10, 2, 15, 127);
        colorBand.setColorAt(color2, 1);
        colorBand.setColorAt(color2, 48);
        colorBand.setColorAt(color2, 49);
        colorBand.setColorAt(color2, 96);
        colorBand.setColorAt(color2, 97);

        ledStrip.setColors(colorBand);

        Thread.sleep(waitingTime);

        colorBand.setAllToSameColor(ARGBColor.BLACK);
        ledStrip.setColors(colorBand);
    }
}


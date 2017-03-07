package fr.craftinglabs.pi.apa102;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.GpioUtil;

import java.time.LocalTime;

public class Apa {

    public static void main(String[] args) throws InterruptedException {

        GpioUtil.enableNonPrivilegedAccess();

        final GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput clock = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "CLOCK", PinState.LOW);
        GpioPinDigitalOutput data = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "DATA", PinState.LOW);

        LedStripIO io = new LedStripGPIO(data, clock);
        Size size = new Size(20, 7);
        
        Frame frame = new Frame(size);
        for (int i = 0; i < frame.getLineNb(); i++) {
            for (int j = 0; j < frame.getColumnNb(); j++) {
                int columnStep = (255 / size.nbColumns()) * j;
                int lineStep = (255 / size.nbLines()) * i;
                ARGBColor color = new ARGBColor(20, columnStep, 150, 255 - lineStep);
                frame.setPixelAt(i, j, color);
            }
        }

        ApaMatrix matrix = new ApaMatrix(size, new Apa102LedStrip(io));

        matrix.print(new Frame(size, ARGBColor.BLACK));

        matrix.print(frame);

        matrix.print(new Frame(size, ARGBColor.BLACK));

        for (int i = 0; i < 1000; i++) {
            matrix.print(TimeFrameBuilder.time(LocalTime.now()));
            Thread.sleep(1000);
        }

        matrix.print(new Frame(size, ARGBColor.BLACK));
    }
}


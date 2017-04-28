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
        Size size = new Size(24, 5);
        
        ApaMatrix matrix = new ApaMatrix(size, new Apa102LedStrip(io));

        matrix.print(new Frame(size, ARGBColor.BLACK));

        for (int i = 0; i < 1000; i++) {
            Frame frame = TimeFrameBuilder.aTimeFrame()
                    .sized(size)
                    .withBackgroundColor(new ARGBColor(1, 75,75,100))
                    .withFont(TimeFont.font())
                    .withFontColor(new ARGBColor(15, 155, 0, 155))
                    .buildForTime(LocalTime.now());

            matrix.print(frame);
            Thread.sleep(1000);
        }

        matrix.print(new Frame(size, ARGBColor.BLACK));
    }
}


package fr.craftinglabs.pi.apa102;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.GpioUtil;
import fr.craftinglabs.pi.apa102.matrix.Apa102LedStrip;
import fr.craftinglabs.pi.apa102.matrix.ApaMatrix;
import fr.craftinglabs.pi.apa102.matrix.Frame;
import fr.craftinglabs.pi.apa102.matrix.Size;
import fr.craftinglabs.pi.apa102.matrix.io.LedStripGPIO;
import fr.craftinglabs.pi.apa102.matrix.io.LedStripIO;

public class Apa {

    public static void main(String[] args) throws InterruptedException {

        GpioUtil.enableNonPrivilegedAccess();

        final GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput clock = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "CLOCK", PinState.LOW);
        GpioPinDigitalOutput data = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "DATA", PinState.LOW);

        LedStripIO io = new LedStripGPIO(data, clock);
        Size size = new Size(750, 5);
        
        ApaMatrix matrix = new ApaMatrix(size, new Apa102LedStrip(io));

        matrix.print(new Frame(size, ARGBColor.BLACK));

        Frame frame = TextFrameBuilder.aTimeFrame()
                .sized(size)
                .withBackgroundColor(new ARGBColor(1, 75,75,100))
                .withFontColor(new ARGBColor(15, 155, 0, 155))
                .buildText("ABCDEFHIJKLMNOPQR", 0);

        matrix.print(frame);
        Thread.sleep(10000);

        matrix.print(new Frame(size, ARGBColor.BLACK));
    }
}


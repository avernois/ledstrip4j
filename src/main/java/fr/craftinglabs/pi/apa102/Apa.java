package fr.craftinglabs.pi.apa102;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.GpioUtil;
import fr.craftinglabs.pi.apa102.matrix.Apa102LedStrip;
import fr.craftinglabs.pi.apa102.matrix.ApaMatrix;
import fr.craftinglabs.pi.apa102.matrix.Frame;
import fr.craftinglabs.pi.apa102.matrix.Size;
import fr.craftinglabs.pi.apa102.matrix.io.ConsoleTwoColorMatrixLedStrip;
import fr.craftinglabs.pi.apa102.matrix.io.LedStripGPIO;
import fr.craftinglabs.pi.apa102.matrix.io.LedStripIO;
import fr.craftinglabs.pi.apa102.text.SlidingTextFramesBuilder;

import static fr.craftinglabs.pi.apa102.text.SlidingTextFramesBuilder.*;

public class Apa {

    public static void main(String[] args) throws InterruptedException {

        GpioUtil.enableNonPrivilegedAccess();

        final GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput clock = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "CLOCK", PinState.LOW);
        GpioPinDigitalOutput data = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "DATA", PinState.LOW);

        LedStripIO io = new LedStripGPIO(data, clock);
        Size size = new Size(24, 5);
        
        ApaMatrix matrix = new ApaMatrix(size, new Apa102LedStrip(io));

        //ApaMatrix matrix = new ApaMatrix(size, new ConsoleTwoColorMatrixLedStrip(size, new ARGBColor(1, 75,75,100)));

        matrix.print(new Frame(size, ARGBColor.BLACK));

        SlidingTextFramesBuilder builder = slidingTextFrames()
                                            .sized(size)
                                            .withBackgroundColor(new ARGBColor(0, 75, 75, 100))
                                            .withFontColor(new ARGBColor(10, 100, 0, 155))
                                            .forText("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");

        for(Frame frame : builder.build()) {
            matrix.print(frame);
            Thread.sleep(100);
        }

        matrix.print(new Frame(size, ARGBColor.BLACK));
    }
}


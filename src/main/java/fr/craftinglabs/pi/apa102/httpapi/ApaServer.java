package fr.craftinglabs.pi.apa102.httpapi;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.GpioUtil;
import fr.craftinglabs.pi.apa102.ARGBColor;
import fr.craftinglabs.pi.apa102.matrix.Apa102LedStrip;
import fr.craftinglabs.pi.apa102.matrix.ApaMatrix;
import fr.craftinglabs.pi.apa102.matrix.Frame;
import fr.craftinglabs.pi.apa102.matrix.Size;
import fr.craftinglabs.pi.apa102.matrix.io.LedStripGPIO;
import fr.craftinglabs.pi.apa102.matrix.io.LedStripIO;
import fr.craftinglabs.pi.apa102.text.SlidingTextFramesBuilder;

import java.io.IOException;

import static fr.craftinglabs.pi.apa102.text.SlidingTextFramesBuilder.slidingTextFrames;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.stop;

/**
 * Main class.
 *
 */
public class ApaServer {

    private static final Integer DEFAULT_PORT = 5000;


    public static void main(String[] args) throws InterruptedException, IOException {
        port(DEFAULT_PORT);

        GpioUtil.enableNonPrivilegedAccess();

        final GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput clock = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "CLOCK", PinState.LOW);
        GpioPinDigitalOutput data = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "DATA", PinState.LOW);

        LedStripIO io = new LedStripGPIO(data, clock);
        Size size = new Size(24, 5);

        ApaMatrix matrix = new ApaMatrix(size, new Apa102LedStrip(io));

        //ApaMatrix matrix = new ApaMatrix(size, new ConsoleTwoColorMatrixLedStrip(size, new ARGBColor(1, 75,75,100)));

        matrix.print(new Frame(size, ARGBColor.BLACK));

        String text = "Port " + DEFAULT_PORT + ".";

        slideText(matrix, text);

        get("/text/:text", ((request, response) -> slideText(matrix, request.params(":text"))));

        addShutdownHook();
        Thread.currentThread().join();
    }

    private static String slideText(ApaMatrix matrix, String text) throws InterruptedException {
        SlidingTextFramesBuilder builder = slidingTextFrames()
                .sized(matrix.size())
                .withBackgroundColor(new ARGBColor(0, 75, 75, 100))
                .withFontColor(new ARGBColor(10, 100, 0, 155))
                .forText(text);

        for(Frame frame : builder.build()) {
            matrix.print(frame);
            Thread.sleep(75);
        }

        return "OK";
    }


    private static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Exiting.");
            stop();
        }, "shutdownHook"));
    }
}